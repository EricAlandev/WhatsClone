
package what.whatjava.services.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
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
