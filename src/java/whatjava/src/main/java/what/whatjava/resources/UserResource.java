package what.whatjava.resources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.services.ResponseRequest.RegisterRequest;
import what.whatjava.services.ResponseRequest.SearchRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users") 
@Validated
public interface UserResource {

    @PostMapping
    CompletableFuture<String>registerUser(@Valid @RequestBody  RegisterRequest user);
    
    @PostMapping("/AddFriends")
    CompletableFuture<List<UserResponseDTO>> searchUsers(@RequestBody SearchRequest search, @RequestHeader("Authorization") String token);

    @PostMapping("/AddFriends/{id}")
    CompletableFuture<String> addFriend (@PathVariable ("id")  @NotNull(message = "ID is required") Long id, @RequestHeader("Authorization") String token);
    
}
