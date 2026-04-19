package what.whatjava.controllers.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.services.loginAndRegister.LoginService;

@RestController 
@RequestMapping("/api/login") 
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    
    @Autowired
    private LoginService loginService; 

    @PostMapping
    public UserResponseDTO postMethodName(@RequestBody EntityUser user) {

        UserResponseDTO callService = loginService.Login(user);

        System.out.println("value from login" + callService);
        
        return callService;
    }
}