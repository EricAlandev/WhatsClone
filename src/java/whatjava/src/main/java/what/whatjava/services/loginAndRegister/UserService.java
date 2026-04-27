
package what.whatjava.services.loginAndRegister; 

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
            //objects to create the relationship of friends.
            EntityUserFriend entityUserFriend = new EntityUserFriend();
            EntityUserFriend entityFriendUser = new EntityUserFriend();

            EntityUser actualUserVerifyed = actualUser.get();
            EntityUser userAddVerifyed = userToAdd.get();

            //creating the user & friend
            entityUserFriend.setFriendsId(userAddVerifyed);
            entityUserFriend.setUserID(actualUserVerifyed);

            userFriendsRepository.save(entityUserFriend);

            //creating the friend & user
            entityFriendUser.setFriendsId(userAddVerifyed);
            entityFriendUser.setUserID(actualUserVerifyed);

            userFriendsRepository.save(entityFriendUser);

            return "You make a new friend: " + userAddVerifyed.getName();
        }

        else{
            return "fail to add the friend.";
        }
    }
}

