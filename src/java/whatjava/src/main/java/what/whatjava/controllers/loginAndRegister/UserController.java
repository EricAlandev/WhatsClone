package what.whatjava.controllers.loginAndRegister; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.SearchDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.resources.UserResource;
import what.whatjava.services.services.loginAndRegister.UserService;


public class UserController implements UserResource{
    
    @Autowired
    private UserService  userService; 

    @Override
    public UserResponseDTO registerUser(EntityUser user) {

        UserResponseDTO callService = userService.saveUser(user);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

    @Override
    public List<UserResponseDTO> searchUsers(SearchDTO search,  String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<UserResponseDTO> callService = userService.searchUsers( search.getSearch() , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

    @Override
    public String addFriend(Long id, @RequestHeader String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        String callService = userService.addFriendService(id , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }
}