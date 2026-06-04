package what.whatjava.services.services.Chat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Value;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityChatVisibleMessages;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.chats.EntityMessagesChat;
import what.whatjava.entitys.logs.EntityMessageLog;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.ChatRepository;
import what.whatjava.repository.MessageLogRepository;
import what.whatjava.repository.MessagesChatRepository;
import what.whatjava.repository.UserFriendsRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.repository.VisibleMessageRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class PullMessagesService implements UseCase<PullMessagesService.InputValues, PullMessagesService.OutPutValues> {
    
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserFriendsRepository userFriendsRepository;

    @Autowired
    MessagesChatRepository messagesChatRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageLogRepository messageLogRepository;

    @Autowired
    VisibleMessageRepository visibleMessageRepository;
    
    @Value
    public static class  InputValues implements UseCase.InputValues{
        String id;
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        List<EntityMessagesChat> messages;
    }

    @Override
    @Transactional
    public OutPutValues execute(InputValues input){

        String id = input.getId();
        String Token = input.getToken();

        Long idLoggedUser = jwtService.authentication(Token);

        Long idOtherUser = Long.parseLong(id);

        //Verify if exist the users;
        EntityUser actualUser = findUser(idLoggedUser);

        EntityUser otherUser = findUser(idOtherUser);

        //verify if they are friends;
        userFriendsRepository.findByUserIDAndFriendsId(actualUser, otherUser)
        .orElseThrow(() -> new RuntimeException("The actual user its not friend of the other user"));

        Optional<EntityChatTable> chatTable = chatRepository.findByUser1AndUser2(actualUser, otherUser)
        .or(() -> chatRepository.findByUser1AndUser2(otherUser, actualUser));

        //message to return (can be fulled or not);
        List<EntityMessagesChat> messagesToReturn = new ArrayList<>();
        
        if(!chatTable.isEmpty()){
            EntityChatTable chatTableFound = chatTable.get();

            List<EntityMessagesChat> messagesChat = findMessagesChats(chatTableFound);

            if(messagesChat.size() > 0){    
               for(int i =0; i < messagesChat.size(); i++){
                    if(messagesChat.get(i).getVisibleMessages().isVisible()){
                        messagesToReturn.add(messagesChat.get(i));
                        //Gonna define that the message was visualized;
                        if(!messagesChat.get(i).getMessageID().getUserID().equals(actualUser)){
                            String status = messagesChat.get(i).getMessageID().getStatus();

                            if(status.equals("not viewed")){
                                messagesChat.get(i).getMessageID().setStatus("Visualized");
                                messagesChatRepository.save(messagesChat.get(i));

                                generateLogs(messagesChat.get(i).getMessageID());
                            }
                        }
                    }
               }
            }
        }

        return new OutPutValues(messagesToReturn);
    }

    public EntityUser findUser(Long id){
            return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User dosn't exist"));
    }

    public List<EntityMessagesChat> findMessagesChats(EntityChatTable chatTableFound){
            //define the newest values
            org.springframework.data.domain.Pageable pageable = PageRequest.of(0,8 , org.springframework.data.domain.Sort.by("id").descending());

            return messagesChatRepository.findMessagesChatsWithVisible(chatTableFound, pageable);
    }

    public void generateLogs (EntityMessage message){
        Timestamp timeNow = new java.sql.Timestamp(System.currentTimeMillis());

        EntityMessageLog messageLog = new EntityMessageLog();

        messageLog.setMessageIdLog(message);
        messageLog.setAction("User visualized the message");
        messageLog.setTime(timeNow);
        messageLog.setUserIdMessage(message.getUserID());
        messageLogRepository.save(messageLog);
    }
}
