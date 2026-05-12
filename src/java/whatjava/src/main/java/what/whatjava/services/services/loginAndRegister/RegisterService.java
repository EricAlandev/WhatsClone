
package what.whatjava.services.services.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Value;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserFriendsRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.ResponseRequest.RegisterRequest;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class RegisterService implements UseCase<RegisterService.InputValues, RegisterService.OutPutValues>{
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserFriendsRepository userFriendsRepository;

    @Autowired
    JwtService jwtService;

    @Value
    public static class InputValues implements UseCase.InputValues {
        RegisterRequest registerRequest; 
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues {
        EntityUser registerResponse; 
    }

    @Override
    public OutPutValues execute(InputValues input){

        EntityUser user = new EntityUser();
        RegisterRequest inputValue = input.getRegisterRequest();

        user.setName(inputValue.getName());
        user.setBirthday(inputValue.getBirthday());
        user.setEmail(inputValue.getEmail());
        user.getNacionality().setCity(inputValue.getCity());;
        user.getNacionality().setCountry(inputValue.getCountry());
        user.getNumber().setDdd(inputValue.getDdd());
        user.getNumber().setNumber(inputValue.getNumber());

        userRepository.save(user);

       return new OutPutValues(user);
    }
        
}
