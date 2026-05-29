package what.whatjava.resources;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.DomainListDTO;
import what.whatjava.dtos.ChatDTO.MessageDTO;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.dtos.TimeToFixDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.services.ResponseRequest.VerifyFixedTimeResponse.ReturnTimeResponse;

@RestController
@RequestMapping("/chat") 
@CrossOrigin(origins = "http://localhost:3000")
public interface ChatResource {

    @GetMapping
        CompletableFuture<List<ChatDTO>> findChats(@RequestHeader("Authorization") String token);

    @GetMapping("/options")
    CompletableFuture<List<DomainListDTO>> pullAllOptions (@RequestHeader("Authorization") String Token);

    @PostMapping("/search")
        CompletableFuture<List<ChatDTO>> searchFriendsToChat(@RequestParam("search") String valueSearch , @RequestHeader("Authorization") String token);

    @GetMapping("/messages/{id}")
        CompletableFuture<List<MessageDTO>>pullMessages(@PathVariable("id") String id, @RequestHeader("Authorization") String token);

    @GetMapping("/user/{id}")
    CompletableFuture<UserResponseDTO> pullUserData (@PathVariable("id") String id,@RequestHeader("Authorization") String Token);
    
    @PostMapping("/messages/{id}")
        CompletableFuture<String> sendMessage(@PathVariable("id") String id, @RequestHeader("Authorization") String token, @RequestBody MesssageDTO message);

    @PutMapping("/messages/ids")
        CompletableFuture<String> editMessage(@RequestParam("id") List<Number> ids, @RequestHeader("Authorization") String token , @RequestBody MesssageDTO message);
    
    @DeleteMapping("/messages/ids")
        CompletableFuture<String> deleteMessages(@RequestParam("id") List<Number> ids, @RequestHeader("Authorization") String token);

    @PutMapping("/messages/fix/ids")
        CompletableFuture<String> fixMessage(@RequestParam("id") List<Number> ids, @RequestHeader("Authorization") String token, @RequestBody TimeToFixDTO timeToFix);

        
    @PutMapping("/messages/verify/fix/ids")
        public CompletableFuture<ReturnTimeResponse> verifyFixMessage(@RequestParam("id") List<Number> ids, @RequestHeader("Authorization") String token);

    @DeleteMapping("/messages/fix/ids")
        CompletableFuture<String> unFixMessage(@RequestParam("id") List<Number> ids, @RequestHeader("Authorization") String token);
}