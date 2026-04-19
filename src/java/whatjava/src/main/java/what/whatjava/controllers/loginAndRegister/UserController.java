package what.whatjava.controllers.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.services.loginAndRegister.UserService;

@RestController 
@RequestMapping("/api/users") 
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserService  userService; 

    @PostMapping
    public UserResponseDTO postMethodName(@RequestBody EntityUser user) {

        UserResponseDTO callService = userService.saveUser(user);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }
}