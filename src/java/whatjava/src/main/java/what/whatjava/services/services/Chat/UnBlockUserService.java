package what.whatjava.services.services.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Value;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.logs.EntityBlockedLog;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.BlockedLogRepository;
import what.whatjava.repository.ChatRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class UnBlockUserService implements UseCase<UnBlockUserService.InputValues, UnBlockUserService.OutPutValues> {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BlockedLogRepository blockedLogRepository;

    @Autowired
    ChatRepository chatRepository;
        
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
    public OutPutValues execute(InputValues input){

        Long idLoggedUser = jwtService.authentication(input.getToken());

        EntityUser loggedUser = userRepository.findById(idLoggedUser)
        .orElseThrow(() -> new RuntimeException("Loggedd user dosn't exist"));

        EntityUser otherUser = userRepository.findById(Long.parseLong(input.getId()))
        .orElseThrow(() -> new RuntimeException("Loggedd user dosn't exist"));

        EntityChatTable chat = chatRepository.findByUser1AndUser2(loggedUser, otherUser)
        .or(() -> chatRepository.findByUser1AndUser2(otherUser, loggedUser))
        .orElseThrow(() -> new RuntimeException("Chat dosn't exist"));
        
        chat.setBlocked(false);

        chatRepository.save(chat);

        //logs
        EntityBlockedLog logs = new EntityBlockedLog();

        logs.setAction("Unblocked");

        return new OutPutValues("He Unblocked");
    }
}
