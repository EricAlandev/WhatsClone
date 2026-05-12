package what.whatjava.resources;

import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.services.ResponseRequest.LoginRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/login") 
public interface LoginResource {

    @PostMapping
    CompletableFuture<UserResponseDTO> loginFunction(@Valid @RequestBody LoginRequest user);
    
}
