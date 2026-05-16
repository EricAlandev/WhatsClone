package what.whatjava.services.services.Chat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.logs.EntityMessageLog;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.MessageLogRepository;
import what.whatjava.repository.MessageRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class FixedMessageService implements UseCase<FixedMessageService.InputValues, FixedMessageService.OutPutValues> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageLogRepository messageLogRepository;

    @Autowired
    private JwtService jwtService;
    

    @Value
    public static class InputValues implements UseCase.InputValues{

        List<Number> ids;
        String token;
        String timeToFix;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        String response;
    }

    @Override
    public OutPutValues execute(InputValues input){
        String token = input.getToken();
        String timeToFix = input.getTimeToFix();
        List<Number> ids = input.getIds();

        Claims claims = jwtService.verifyToken(token);
        Long idUser = claims.get("id", Long.class);

        //verify if user exists;
        EntityUser user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("user dosn't exist"));

        //find and delete the messages
        for(int i = 0; i < ids.size(); i++){
            Number actualId = ids.get(i);

            EntityMessage message = messageRepository.findById(actualId).orElseThrow(() -> new RuntimeException("Message not found"));

            if(message.isFixed() == false){
                message.setFixed(true);
            }

            //verify how mutch time
            Timestamp time = new Timestamp(System.currentTimeMillis());

            LocalDateTime ldt = time.toLocalDateTime();

            switch (timeToFix) {
                case "24h":
                    ldt = ldt.plusDays(1);
                    break;

                case "7d":
                    ldt = ldt.plusDays(7);
                    break;

                case "30d":
                    ldt = ldt.plusDays(30);
                    break;
        
                default:
                    break;
            }

            time = Timestamp.valueOf(ldt);

            message.setEnd_fixed(time);
            messageRepository.save(message);
            
            //logs
            EntityMessageLog messageLog = new EntityMessageLog();

            messageLog.setAction("Fix the message");
            messageLog.setMessageIdLog(message);
            messageLog.setTime(time);
            messageLog.setUserIdMessage(user);
            messageLogRepository.save(messageLog);
        }

        return new OutPutValues("Messages deleted with sucess!");
    }
}
