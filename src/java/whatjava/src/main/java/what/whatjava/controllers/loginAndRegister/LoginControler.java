package what.whatjava.controllers.loginAndRegister; 

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.resources.LoginResource;
import what.whatjava.services.ResponseRequest.LoginRequest;
import what.whatjava.services.ResponseRequest.LoginResponse;
import what.whatjava.services.services.ServiceExecute;
import what.whatjava.services.services.loginAndRegister.LoginService;

@RestController 
@CrossOrigin
public class LoginControler implements LoginResource {
    
    @Autowired
    private LoginService loginService; 

    @Override 
    public CompletableFuture<UserResponseDTO> loginFunction( LoginRequest user) {
        return ServiceExecute.execute(
            loginService,
            new LoginService.InputValues(user),
            // Ensure out.getLoginResponse() returns the EntityUser 
            // needed by LoginResponse.from()
            (out) -> LoginResponse.from(out.getLoginResponse()) 
        );
    }
}