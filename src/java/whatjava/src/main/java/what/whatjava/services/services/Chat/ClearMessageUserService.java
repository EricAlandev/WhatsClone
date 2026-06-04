package what.whatjava.services.services.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Value;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityChatVisibleMessages;
import what.whatjava.entitys.chats.EntityMessagesChat;
import what.whatjava.entitys.logs.EntityBlockedLog;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.BlockedLogRepository;
import what.whatjava.repository.ChatRepository;
import what.whatjava.repository.MessageRepository;
import what.whatjava.repository.MessagesChatRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.repository.VisibleMessageRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class ClearMessageUserService implements UseCase<ClearMessageUserService.InputValues, ClearMessageUserService.OutPutValues> {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessagesChatRepository messagesChatRepository;

    @Autowired
    VisibleMessageRepository visibleMessageRepository;

    @Autowired
    BlockedLogRepository blockedLogRepository;
        
    @Value
    public static class InputValues implements UseCase.InputValues{
        String id;
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        String message;
    }

    @Override
    @Transactional
    public OutPutValues execute(InputValues input){

        Long idLoggedUser = jwtService.authentication(input.getToken());

        //find users;
        EntityUser loggedUser = userRepository.findById(idLoggedUser)
        .orElseThrow(() -> new RuntimeException("Loggedd user dosn't exist"));

        EntityUser otherUser = userRepository.findById(Long.parseLong(input.getId()))
        .orElseThrow(() -> new RuntimeException("Loggedd user dosn't exist"));

        //find chat
        EntityChatTable chat = chatRepository.findByUser1AndUser2(loggedUser, otherUser)
        .or(() -> chatRepository.findByUser1AndUser2(otherUser, loggedUser))
        .orElseThrow(() -> new RuntimeException("Chat dosn't exist"));
        
        //find messages
        List<EntityMessagesChat> messages = messagesChatRepository.findByChatTableID(chat);

        if(messages.size() > 0){
            for(int i = 0; i < messages.size(); i++){
                 //turn the message to not visible to this user;
                EntityChatVisibleMessages actualMessage = visibleMessageRepository.findByChatVisibleMessages(messages.get(i))
                .orElseThrow(() -> new RuntimeException("message dosn't exist"));

                actualMessage.setVisible(false);

                visibleMessageRepository.save(actualMessage);
            }
        }

        return new OutPutValues("Cleaned Messages");
    }
}
