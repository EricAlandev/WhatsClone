package what.whatjava.services.services.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class PullUserDataService implements UseCase<PullUserDataService.InputValues, PullUserDataService.OutPutValues> {

    @Autowired
    UserRepository userRepository;

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
    }

    @Override
    public OutPutValues execute(InputValues input){
        String idUser = input.getId();
        String Token = input.getToken();

        Claims claims = jwtService.verifyToken(Token);
        Long idLoggedUser = claims.get("id", Long.class);

        //verify if user exist;
        userRepository.findById(idLoggedUser).orElseThrow(() -> new RuntimeException("User that maked the request dosn't exist"));

        EntityUser user = userRepository.findById(Long.parseLong(idUser)).orElseThrow(() -> new RuntimeException("User that maked the request dosn't exist"));

        return new OutPutValues(user);
    }
}