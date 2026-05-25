package what.whatjava.services.services.Chat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.Value;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.MessageRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class VerifyFixedTimeService implements UseCase<VerifyFixedTimeService.InputValues, VerifyFixedTimeService.OutPutValues> {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Value
    public static class InputValues implements UseCase.InputValues{
        List<Number> ids;
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        String response;
        List<EntityMessage> message;
    }

    @Transactional
    public OutPutValues execute(InputValues input){

        System.out.println("Inside of the verifyFixed" + input.getToken() + input.getIds());
        
        String Token = input.getToken();
        List<Number> ids = input.getIds();

        Claims claims = jwtService.verifyToken(Token);
        Long Id = claims.get("id", Long.class);

        EntityUser user = userRepository.findById(Id).orElseThrow(() -> new RuntimeException("User dosn't exist"));

        //return of the service;
        List<EntityMessage> changedMessages = new ArrayList<>();

        for(int i = 0; i < ids.size(); i++){

            Number actualId = ids.get(i);

            EntityMessage message = messageRepository.findByIdAndUserID(actualId, user).orElseThrow(() -> new RuntimeException("Message not found"));

            Timestamp time = new Timestamp(System.currentTimeMillis());

            Long actualTime = time.getTime();
            Long endFixedTimeMessage =  message.getEnd_fixed().getTime();

            if(actualTime >= endFixedTimeMessage){
                message.setFixed(false);
                message.setEnd_fixed(time);
                messageRepository.save(message);

                changedMessages.add(message);
            }
        }

        String messageReturn = "Any message got changed";

        if(changedMessages.size() > 0){
            messageReturn = "The message(s) got changed";
        }
        
        return new OutPutValues(messageReturn, changedMessages);
    }
}
