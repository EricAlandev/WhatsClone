package what.whatjava.services.services.Chat;
import java.util.Optional;

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
public class PullUserDataService implements UseCase<PullUserDataService.InputValues, PullUserDataService.OutPutValues> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    BlockedLogRepository blockedLogRepository;

    @Autowired
    JwtService jwtService;
    
    @Value
    public static class InputValues implements UseCase.InputValues{
        private String id;
        private String Token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        EntityUser findedUser;
        boolean blocked;
        boolean loggedUserWhoBlock;
    }

    @Override
    public OutPutValues execute(InputValues input){
        String idOtherUser = input.getId();
        String Token = input.getToken();

        Long idLoggedUser = jwtService.authentication(Token);

        //verify if user exist;
        EntityUser loggedUser = userRepository.findById(idLoggedUser).orElseThrow(() -> new RuntimeException("User that maked the request dosn't exist"));

        EntityUser otherUser = userRepository.findById(Long.parseLong(idOtherUser)).orElseThrow(() -> new RuntimeException("User that maked the request dosn't exist"));

        Optional<EntityChatTable> chat = chatRepository.findByUser1AndUser2(loggedUser, otherUser)
        .or(() -> chatRepository.findByUser1AndUser2(otherUser, loggedUser));

        boolean blocked = false;
        boolean loggedUserWhoBlock = false;

        if(!chat.isEmpty() && chat != null){
            EntityChatTable foundedChatTable = chat.get();

            if(foundedChatTable.isBlocked() == true){

                //by the blocked logs, gonna verify who blocked
                Optional<EntityBlockedLog> blockedLog = blockedLogRepository.findLatestBlockByUser(loggedUser, otherUser)
                .or(() -> blockedLogRepository.findLatestBlockByUser(otherUser, loggedUser));

                if(loggedUser.equals(blockedLog.get().getUserWhoFetch())){
                    loggedUserWhoBlock = true;
                }

                blocked = true;
            }
        }

        return new OutPutValues(otherUser, blocked, loggedUserWhoBlock);
    }
}