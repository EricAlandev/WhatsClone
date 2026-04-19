package what.whatjava.services.loginAndRegister; 

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.JwtService;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;
    
    public UserResponseDTO Login(EntityUser user){

                //try the search
                Optional<EntityUser> userFromDatabase = userRepository.findByEmail(user.getEmail());

                //throw if don't find
                if(userFromDatabase == null || userFromDatabase.isEmpty()){
                    throw new RuntimeException("User dosn't exists");
                }

                EntityUser userFinded = userFromDatabase.get();
                //compare the passwords
                if(!userFinded.getPassword().equals(user.getPassword())){
                    throw new RuntimeException("Password its wrong");
                }  
                
                String token = jwtService.generateToken(userFinded);

                //update the token and generate the login;
                userFinded.setToken(token);
                userRepository.save(userFinded);

                return UserResponseDTO.builder()
                .id(userFinded.getId())
                .name(userFinded.getName())
                .token(userFinded.getToken())
                .build();
                }
}
