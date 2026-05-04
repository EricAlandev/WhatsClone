package what.whatjava.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.dtos.ChatDTO.MessageDTO;
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

        System.out.println("Token value:" + cleanToken);

        List<ChatDTO> callService = chatService.findAllChats(cleanToken);

        System.out.println("value comming to front-end pull findChats" + callService);
        
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
        public List<MessageDTO> pullMessages(@PathVariable("id") String id, @RequestHeader("Authorization") String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<MessageDTO> callService = chatService.findMessages(id , cleanToken);

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

    @PutMapping("/messages/ids")
    public String editMessage(@RequestParam("id") List<Number> ids, @RequestHeader("Authorization") String token , @RequestBody MesssageDTO message) {

    String cleanToken = (token.startsWith("Bearer ") 
            ? token.substring(7) : token
    );

    String callService = chatService.alterMessage(ids , cleanToken, message.getMessage());

    System.out.println("value comming to front-end " + callService);
    
    return callService;
    }
    

    @DeleteMapping("/messages/ids")
    public String sendMessage(@RequestParam("id") List<Number> ids, @RequestHeader("Authorization") String token) {

    String cleanToken = (token.startsWith("Bearer ") 
            ? token.substring(7) : token
    );

    String callService = chatService.deleteMessages(ids , cleanToken);

    System.out.println("value comming to front-end " + callService);
    
    return callService;
}
}