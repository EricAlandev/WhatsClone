
package what.whatjava.services.services.loginAndRegister; 

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.entitys.users.EntityUserFriend;
import what.whatjava.repository.UserFriendsRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class UserService{


    @Autowired
    UserRepository userRepository;
    
    @Autowired
    JwtService jwtService;
    
    //SAVE USER
    public UserResponseDTO saveUser(EntityUser user){

        if(user.getNumber() != null){
            user.getNumber().setUser(user);
        }

        if(user.getNacionality() != null){
            user.getNacionality().setUserNacionality(user);
        }
        
        EntityUser save = userRepository.save(user);

        return UserResponseDTO.builder()
            .id(save.getId())
            .name(save.getName())
            .email(save.getEmail())
            .nacionality(new UserResponseDTO.NacionalityDTO(save.getNacionality().getCity(), save.getNacionality().getCountry()))
            .number(new UserResponseDTO.NumberDTO(save.getNumber().getDdd(), save.getNumber().getNumber()))
            .build();
    }

}

