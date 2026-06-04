package what.whatjava.services.services.Chat;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Value;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.logs.EntityBlockedLog;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.BlockedLogRepository;
import what.whatjava.repository.ChatRepository;
import what.whatjava.repository.MessageLogRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class BlockUserService implements UseCase<BlockUserService.InputValues, BlockUserService.OutPutValues> {

    @Autowired
    MessageLogRepository messageLogRepository;

    @Autowired
    BlockedLogRepository blockedLogRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    JwtService jwtService;
    
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

        Long idUser = jwtService.authentication(input.getToken());
        Long idOtherUser = Long.parseLong(input.getId());

        EntityUser userWhoMakeRequest = findUser(idUser);
        EntityUser userWhoGonnaBeBlocked = findUser(idOtherUser);

        EntityChatTable chat = findChat(userWhoMakeRequest, userWhoGonnaBeBlocked);
        
        chat.setBlocked(true);
        chatRepository.save(chat);

        blockUser(userWhoMakeRequest, userWhoGonnaBeBlocked);

        return new OutPutValues("User got Blocked");
    }

    public EntityChatTable findChat(EntityUser userWhoMakeRequest, EntityUser userWhoGonnaBeBlocked){

        return chatRepository.findByUser1AndUser2(userWhoMakeRequest, userWhoGonnaBeBlocked)
        .or(() -> chatRepository.findByUser1AndUser2(userWhoGonnaBeBlocked, userWhoMakeRequest))
        .orElseThrow(() -> new RuntimeException("Chat dosn't exist"));
    }

    public EntityUser findUser(Long id){
        return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User dosn't exist"));
    }

    public void blockUser (EntityUser userWhoMakeRequest, EntityUser userWhoGonnaBeBlocked){
        Timestamp now = Timestamp.from(Instant.now());

        EntityBlockedLog blocked = new EntityBlockedLog();

        blocked.setTime(now);
        blocked.setAction("blocked the other one");
        blocked.setUserWhoFetch(userWhoMakeRequest);
        blocked.setAffectedUser(userWhoGonnaBeBlocked);

        blockedLogRepository.save(blocked);
    }
}
