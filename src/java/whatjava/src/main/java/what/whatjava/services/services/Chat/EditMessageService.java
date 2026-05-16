package what.whatjava.services.services.Chat;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class EditMessageService implements UseCase<EditMessageService.InputValues, EditMessageService.OutPutValues>{

        @Autowired
        private MessageRepository messageRepository;

        @Autowired
        private MessageLogRepository messageLogRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtService jwtService;

        @Value
        public static class InputValues implements UseCase.InputValues{

            @Size(min = 1, max = 10)
            private List<Number> ids; 

            @NotBlank(message = "Token cannot be blank")
            @NotEmpty(message = "Token cannot be empty")
            @NotNull(message = "Token cannot be null")
            private String Token;

            @NotBlank(message = "Token cannot be blank")
            @NotEmpty(message = "Token cannot be empty")
            @NotNull(message = "Token cannot be null")
            String message;
        }

        @Value
        public static class OutPutValues implements UseCase.OutPutValues{
            String response;
        }

        @Override
        public OutPutValues execute(InputValues input){

            String token = input.getToken();
            List<Number> ids = input.getIds();
            String message = input.getMessage();

            Claims claim = jwtService.verifyToken(token);
            Long id = claim.get("id", Long.class);

            EntityUser user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User dosn't exist"));

            Number idMessage = ids.get(0);

            EntityMessage messageDB = messageRepository.findById(idMessage).orElseThrow(() -> new RuntimeException("message dosn't exist"));

            messageDB.setMessage(message);
            messageDB.setEdited(true);

            Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

            messageDB.setTime(now);
            messageRepository.save(messageDB);

            //create the log
            EntityMessageLog messageLog = new EntityMessageLog();

            messageLog.setMessageIdLog(messageDB);
            messageLog.setAction("Changed the message");
            messageLog.setTime(now);
            messageLog.setUserIdMessage(user);
            messageLogRepository.save(messageLog);


            return new OutPutValues("message edited with sucess");
        }
}
