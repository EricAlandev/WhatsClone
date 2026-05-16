package what.whatjava.services.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.entitys.users.EntityUserFriend;
import what.whatjava.repository.UserFriendsRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class AddFriendService implements UseCase<AddFriendService.InputValues, AddFriendService.OutPutValues> {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserFriendsRepository userFriendsRepository;

    @Autowired
    UserRepository userRepository;
    

    @Value
    public static class InputValues implements UseCase.InputValues{
        Long id;
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        EntityUser userToAdd;
        EntityUser userAdded;
    }

    @Override
    public OutPutValues execute(InputValues input){
        String Token = input.getToken();
        Long id = input.getId();

        Claims claim = jwtService.verifyToken(Token);
        Long userId = claim.get("id", Long.class);

         if(userId == id){
            throw new RuntimeException("You can't add yourself");        
        }

        EntityUser actualUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Error to find the user who tryed to add"));
        
        EntityUser userToAdd = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Error to find the user who would be add"));;

        //objects to create the relationship of friends.
        EntityUserFriend entityUserFriend = new EntityUserFriend();

        //creating the user & friend
        entityUserFriend.setFriendsId(actualUser);
        entityUserFriend.setUserID(userToAdd);

        userFriendsRepository.save(entityUserFriend);

        //creating the friend & user
        entityUserFriend.setFriendsId(userToAdd);
        entityUserFriend.setUserID(actualUser);

        userFriendsRepository.save(entityUserFriend);

        return new OutPutValues(actualUser, userToAdd);
        }
}