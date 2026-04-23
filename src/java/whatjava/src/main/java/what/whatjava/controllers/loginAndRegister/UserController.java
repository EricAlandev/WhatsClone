package what.whatjava.controllers.loginAndRegister; 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.SearchDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.services.loginAndRegister.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/users") 
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserService  userService; 

    @PostMapping
    public UserResponseDTO registerUser(@RequestBody EntityUser user) {

        UserResponseDTO callService = userService.saveUser(user);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

    @PostMapping("/AddFriends")
    public List<UserResponseDTO> searchUsers(@RequestBody SearchDTO search, @RequestHeader("Authorization") String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<UserResponseDTO> callService = userService.searchUsers( search.getSearch() , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

    @PostMapping("/AddFriends/{id}")
    public String searchUsers(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        String callService = userService.addFriendService(id , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }
}