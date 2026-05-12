package what.whatjava.services.services.loginAndRegister; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.Value;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.ResponseRequest.LoginRequest;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class LoginService implements UseCase<LoginService.InputValues, LoginService.OutputValues> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Value
    public static class InputValues implements UseCase.InputValues {
        LoginRequest loginRequest; 
    }

    @Value
    public static class OutputValues implements UseCase.OutPutValues {
        EntityUser loginResponse; 
    }

    @Override
    public OutputValues execute(InputValues input) {
        // Use the data from the input wrapper
        String email = input.loginRequest.getEmail();
        String password = input.loginRequest.getPassword();

        EntityUser user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User doesn't exist"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Password is wrong");
        }

        String token = jwtService.generateToken(user);
        user.setToken(token);
        userRepository.save(user);

        return new OutputValues(user);
    }
}
