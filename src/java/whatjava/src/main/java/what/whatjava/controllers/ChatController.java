package what.whatjava.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.dtos.SearchDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.services.ChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/chat") 
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {
    
    @Autowired
    private ChatService chatService; 

        @GetMapping
        public List<ChatDTO> findChats(@RequestHeader("Authorization") String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<ChatDTO> callService = chatService.findAllChats(cleanToken);

        System.out.println("value comming to front-end pull" + callService);
        
        return callService;
        }

        @PostMapping("/search")
        public List<ChatDTO> searchFriendsToChat(@RequestParam("search") String valueSearch , @RequestHeader("Authorization") String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<ChatDTO> callService = chatService.searchFriendsService(valueSearch , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

        @GetMapping("/messages/{id}")
        public List<ChatDTO> pullMessages(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<ChatDTO> callService = chatService.findMessages(id , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

        @PostMapping("/messages/{id}")
        public String sendMessage(@PathVariable("id") String id, @RequestHeader("Authorization") String token, @RequestBody MesssageDTO message) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        System.out.println("value from post" + id + message.getMessage() + token);

        String callService = chatService.sendMessage(id , cleanToken, message.getMessage());

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }
}