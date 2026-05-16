package what.whatjava.services.services.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.ChatRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;


@Service
public class FindChatsService implements UseCase<FindChatsService.InputValues, FindChatsService.OutPutValues>{

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    @Value
    public static class InputValues implements UseCase.InputValues{
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        List<EntityChatTable> chats;
        EntityUser user;
    }
    

    @Override
    public OutPutValues execute(InputValues input){

        String token = input.getToken();
        //verify token;
        Claims claims = jwtService.verifyToken(token);
        Long idUser = claims.get("id", Long.class);

        EntityUser user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User dosn't exist"));

        List<EntityChatTable> chats = chatRepository.findByUser1OrUser2(user, user);

        return new OutPutValues(chats, user);
    }
}
