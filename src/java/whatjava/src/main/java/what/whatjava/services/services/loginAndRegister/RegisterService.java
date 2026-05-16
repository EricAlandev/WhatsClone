
package what.whatjava.services.services.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Value;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.entitys.users.EntityUserNacionality;
import what.whatjava.entitys.users.EntityUserNumber;
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
    @Transactional
    public OutPutValues execute(InputValues input){

        EntityUser user = new EntityUser();
        EntityUserNumber number = new EntityUserNumber();
        EntityUserNacionality nacionality = new EntityUserNacionality();

        RegisterRequest inputValue = input.getRegisterRequest();

        user.setName(inputValue.getName());
        user.setBirthday(inputValue.getBirthday());
        user.setEmail(inputValue.getEmail());
        user.setPassword(inputValue.getPassword());

        nacionality.setCity(inputValue.getCity());
        nacionality.setCountry(inputValue.getCountry());
        nacionality.setUserNacionality(user);

        number.setDdd(inputValue.getDdd());
        number.setNumber(inputValue.getNumber());
        number.setUser(user);


        user.setNacionality(nacionality);
        user.setNumber(number);

        userRepository.save(user);

       return new OutPutValues(user);
    }
        
}
