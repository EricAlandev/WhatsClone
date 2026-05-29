package what.whatjava.services.services.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.Value;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class AccountService implements UseCase<AccountService.InputValues, AccountService.OutPutValues> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Value
    public static class InputValues implements UseCase.InputValues{
        String token;
        UserResponseDTO user;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        String message;
        List<String> updatedOptions;
    }

    @Override
    @Transactional
    public OutPutValues execute(InputValues input){
        String Token = input.getToken();
        Claims claims = jwtService.verifyToken(Token);
        
        Long id = claims.get("id", Long.class);

        EntityUser userFromDB = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user dosn't exist"));

        //entity maked to in the final of the service verify if the userFromDB got any change;
        EntityUser userToVerify = userFromDB;

        //Value that gonna be updated if necessary
        UserResponseDTO dataUser = input.getUser();

        List<String> updatedOptions = new ArrayList<>();
        
        if(!dataUser.getName().isBlank() && !userFromDB.getName().equals(dataUser.getName())){
            userFromDB.setName(dataUser.getName());

            updatedOptions.add("Name");
        }

        if(!dataUser.getEmail().isBlank() && !userFromDB.getEmail().equals(dataUser.getEmail())){
            userFromDB.setEmail(dataUser.getEmail());

            updatedOptions.add("Email");
        }

        if(!dataUser.getDescription().isBlank() && !userFromDB.getDescription().equals(dataUser.getDescription())){
            userFromDB.setDescription(dataUser.getDescription());

            updatedOptions.add("Description");
        }

        if(!dataUser.getBirthday().isBlank() && !userFromDB.getBirthday().equals(dataUser.getBirthday())){
            userFromDB.setBirthday(dataUser.getBirthday());

            updatedOptions.add("Birthday");
        }

        if(!dataUser.getNumber().getDdd().isBlank() && !userFromDB.getNumber().getDdd().equals(dataUser.getNumber().getDdd())){
            userFromDB.getNumber().setDdd(dataUser.getNumber().getDdd());

            updatedOptions.add("DDD");
        }

        if(!dataUser.getNumber().getNumber().isBlank() && !userFromDB.getNumber().getNumber().equals(dataUser.getNumber().getNumber())){
            userFromDB.getNumber().setNumber(dataUser.getNumber().getNumber());

            updatedOptions.add("Number");
        }

        if(!dataUser.getNacionality().getCity().isBlank() && !userFromDB.getNacionality().getCity().equals(dataUser.getNacionality().getCity())){
            userFromDB.getNacionality().setCity(dataUser.getNacionality().getCity());

            updatedOptions.add("City");
        }

        if(!dataUser.getNacionality().getCountry().isBlank() && !userFromDB.getNacionality().getCountry().equals(dataUser.getNacionality().getCountry())){
            userFromDB.getNacionality().setCountry(dataUser.getNacionality().getCountry());

            updatedOptions.add("Country");
        }

        //throw if any value got updated;
        if(updatedOptions.size() == 0){
            throw new RuntimeException("Any data got updated");
        }

        String returnMessage = "Got updated the: ";

        if(!userFromDB.equals(userToVerify)){
            userRepository.save(userFromDB);
        }

        return new OutPutValues(returnMessage, updatedOptions);
    }
    
}
