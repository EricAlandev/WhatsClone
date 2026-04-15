package what.whatjava.controllers.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import what.whatjava.entitys.users.EntityUser;
import what.whatjava.services.loginAndRegister.LoginService;

@RestController 
@RequestMapping("/api/login") 
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    
    @Autowired
    private LoginService loginService; 

    @PostMapping
    public ResponseEntity<EntityUser> postMethodName(@RequestBody EntityUser user) {

        EntityUser callService = loginService.Login(user);
        
        return ResponseEntity.ok(callService);
    }
}