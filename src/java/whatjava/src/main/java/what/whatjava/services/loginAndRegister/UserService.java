
package what.whatjava.services.loginAndRegister; 

import java.lang.classfile.ClassFile.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.entitys.users.EntityUserFriend;
import what.whatjava.repository.UserFriendsRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.JwtService;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    UserFriendsRepository userFriendsRepository;

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

    //SEARCH USERS
    public List<UserResponseDTO> searchUsers(String name, String token){

        //verify token
        jwtService.verifyToken(token);
        
        System.out.println("user values" + name);

        List<EntityUser> findedUsers = userRepository.findByNameContainingIgnoreCase(name);

        System.out.println("Finded Users values" + findedUsers);

        if(findedUsers.size() == 0){
            List<UserResponseDTO> list = new ArrayList();
            return list;
        }

        return findedUsers.stream()
            .map(list -> UserResponseDTO.builder()
                .id(list.getId())
                .name(list.getName())
                .email(list.getEmail())
                .description(list.getDescription())
                .build()
            ).toList();
    }

    //Add friend
    public String addFriendService(Long id, String token){

        //verify token
        Claims claim = jwtService.verifyToken(token);
        Long userId = claim.get("id", Long.class);
        
        System.out.println(userId + id);
        //not allow the user to auto add himself
        if(userId == id){
            throw new RuntimeException("You can't add yourself");        
        }

        Optional<EntityUser> actualUser = userRepository.findById(userId);
        
        Optional<EntityUser> userToAdd = userRepository.findById(id);

        if(actualUser != null && !actualUser.isEmpty()){
            EntityUserFriend entityUserFriend = new EntityUserFriend();

            EntityUser actualUserVerifyed = actualUser.get();
            EntityUser userAddVerifyed = userToAdd.get();

            entityUserFriend.setFriends_id(userAddVerifyed);
            entityUserFriend.setUserID(actualUserVerifyed);

            userFriendsRepository.save(entityUserFriend);

            return "You make a new friend: " + userAddVerifyed.getName();
        }

        else{
            return "fail to add the friend.";
        }
    }
}

