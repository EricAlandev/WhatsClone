package what.whatjava.controllers.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import what.whatjava.entitys.users.EntityUser;
import what.whatjava.services.loginAndRegister.UserService;

@RestController 
@RequestMapping("/api/users") 
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    
    @Autowired
    private UserService  userService; 

    @PostMapping
    public ResponseEntity<EntityUser> postMethodName(@RequestBody EntityUser user) {

        EntityUser callService = userService.saveUser(user);
        
        return ResponseEntity.ok(callService);
    }
}