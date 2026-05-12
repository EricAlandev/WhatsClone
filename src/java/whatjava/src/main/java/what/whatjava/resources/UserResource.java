package what.whatjava.resources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import what.whatjava.dtos.SearchDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.services.ResponseRequest.RegisterRequest;

@RestController
@RequestMapping("/api/users") 
@CrossOrigin(origins = "http://localhost:3000")
public interface UserResource {

    @PostMapping
    CompletableFuture<String>registerUser(@RequestBody  RegisterRequest user);
    
    @PostMapping("/AddFriends")
    List<UserResponseDTO> searchUsers(@RequestBody SearchDTO search, @RequestHeader("Authorization") String token);

    @PostMapping("/AddFriends/{id}")
    String addFriend (@PathVariable("id") Long id, @RequestHeader("Authorization") String token);
    
}
