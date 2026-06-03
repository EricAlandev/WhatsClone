package what.whatjava.services.services.Chat;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityChatVisibleMessages;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.chats.EntityMessagesChat;
import what.whatjava.entitys.logs.EntityMessageLog;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.ChatRepository;
import what.whatjava.repository.MessageLogRepository;
import what.whatjava.repository.MessageRepository;
import what.whatjava.repository.MessagesChatRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.repository.VisibleMessageRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class SendMessageService implements UseCase<SendMessageService.InputValues, SendMessageService.OutPutValues> {
    
    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessagesChatRepository messagesChatRepository;

    @Autowired
    ChatRepository chatRepository; 

    @Autowired
    MessageLogRepository messageLogRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    VisibleMessageRepository visibleMessageRepository;
    
    @Value
    public static class  InputValues implements UseCase.InputValues{
        String id;
        String cleanToken;
        MesssageDTO message;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        String responseMeessage;
    }

    @Override
    public OutPutValues execute(InputValues input){
        try {

            String id = input.getId();
            String Token = input.getCleanToken();
            String message = input.getMessage().getMessage();

            Long idActualUser = jwtService.authentication(Token);
            Long idOtherUser = Long.parseLong(id);

            //find users
            EntityUser actualUser = userRepository.findById(idActualUser).orElseThrow(() -> new RuntimeException("Actual user not found"));

            EntityUser otherUser = userRepository.findById(idOtherUser).orElseThrow(() -> new RuntimeException("other user not found"));

            //find table or create
            Optional<EntityChatTable> chatTableFound = chatRepository.findByUser1AndUser2(actualUser, otherUser).or(() -> chatRepository.findByUser1AndUser2(otherUser, actualUser));

            EntityChatTable chatTable = new EntityChatTable();

            //Create the table or note;
            if(chatTableFound.isEmpty()){
                    EntityChatTable newChatTable = new EntityChatTable();
                
                    newChatTable.setUser1(actualUser);
                    newChatTable.setUser2(otherUser);
                    newChatTable.setBlocked(false);
                    chatRepository.save(newChatTable);
                    chatTable = newChatTable;
            }

            else{
                chatTable = chatTableFound.get();
            }

            if(chatTable.isBlocked() == false){
                //create message
                EntityMessage messageValue = new EntityMessage();
                Timestamp timeNow = new java.sql.Timestamp(System.currentTimeMillis());

                messageValue.setMessage(message);
                messageValue.setStatus("not viewed");
                messageValue.setUserID(actualUser);
                messageValue.setTime(timeNow);
                messageValue.setEdited(false);
                messageValue.setFixed(false);
                messageValue.setEnd_fixed(timeNow);
                messageRepository.save(messageValue);

                //visuability
                EntityMessagesChat messagesChat = new EntityMessagesChat();

                messagesChat.setChatTableID(chatTable);
                messagesChat.setMessageID(messageValue);
                messagesChatRepository.save(messagesChat);

                EntityChatVisibleMessages vM = new EntityChatVisibleMessages();

                vM.setChatVisibleMessages(messagesChat);
                vM.setUserVisibleMessage(otherUser);
                vM.setVisible(true);

                visibleMessageRepository.save(vM);

                //create the log
                EntityMessageLog messageLog = new EntityMessageLog();

                messageLog.setMessageIdLog(messageValue);
                messageLog.setAction("Message sended");
                messageLog.setTime(timeNow);
                messageLog.setUserIdMessage(actualUser);
                messageLogRepository.save(messageLog);

                return new OutPutValues("200 - message");
            }

            else{
                return new OutPutValues("This user is blocked");
            }

        } catch (Exception e) {
            return new OutPutValues("fail in the process of send the message");
        }
    }
}
