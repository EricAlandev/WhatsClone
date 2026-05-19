package what.whatjava.services.services.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.repository.MessageRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class UnFixMessageService implements UseCase<UnFixMessageService.InputValues, UnFixMessageService.OutPutValues>{

    @Autowired
    JwtService jwtService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

     @Value
    public static class InputValues implements UseCase.InputValues{

        List<Number> ids;
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        String response;
    }

    @Override
    public OutPutValues execute(InputValues input){

        Claims claims = jwtService.verifyToken(input.getToken());
        Long idUser = claims.get("id", Long.class);

        userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User dosn't exist"));


        for(int i = 0; i < input.getIds().size(); i++){
            Number actualId = input.getIds().get(i);

           EntityMessage message = messageRepository.findById(actualId).orElseThrow(() -> new RuntimeException("message dosn't exist"));

           if(message.isFixed()){
             message.setFixed(false);
             messageRepository.save(message);
           }
        }


        return new OutPutValues("Fixed unfixed");
    }
    
}
