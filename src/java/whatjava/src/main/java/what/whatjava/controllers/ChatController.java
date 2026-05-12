package what.whatjava.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.dtos.TimeToFixDTO;
import what.whatjava.dtos.ChatDTO.MessageDTO;
import what.whatjava.resources.ChatResource;
import what.whatjava.services.services.Chat.ChatService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/chat") 
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController implements ChatResource {
    
    @Autowired
    private ChatService chatService; 

        @Override
        public List<ChatDTO> findChats(String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        System.out.println("Token value:" + cleanToken);

        List<ChatDTO> callService = chatService.findAllChats(cleanToken);

        System.out.println("value comming to front-end pull findChats" + callService);
        
        return callService;
        }

        @Override
        public List<ChatDTO> searchFriendsToChat(String valueSearch ,String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<ChatDTO> callService = chatService.searchFriendsService(valueSearch , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

        @Override
        public List<MessageDTO> pullMessages(String id, String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<MessageDTO> callService = chatService.findMessages(id , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

        @Override
        public String sendMessage(String id, String token,  MesssageDTO message) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        System.out.println("value from post" + id + message.getMessage() + token);

        String callService = chatService.sendMessage(id , cleanToken, message.getMessage());

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

    @PutMapping("/messages/ids")
    public String editMessage(List<Number> ids, String token ,   MesssageDTO message) {

    String cleanToken = (token.startsWith("Bearer ") 
            ? token.substring(7) : token
    );

    String callService = chatService.alterMessage(ids , cleanToken, message.getMessage());

    System.out.println("value comming to front-end " + callService);
    
    return callService;
    }
    

    @DeleteMapping("/messages/ids")
    public String sendMessage(List<Number> ids, String token) {

    String cleanToken = (token.startsWith("Bearer ") 
            ? token.substring(7) : token
    );

    String callService = chatService.deleteMessages(ids , cleanToken);

    System.out.println("value comming to front-end " + callService);
    
    return callService;
}

    @PutMapping("/messages/fix/ids")
    public String fixMessage(List<Number> ids, String token,   TimeToFixDTO timeToFix) {

    String cleanToken = (token.startsWith("Bearer ") 
            ? token.substring(7) : token
    );

    System.out.println("value comming from the front-end \n\n\n\n\n\n\n\n" + cleanToken);

    String callService = chatService.fixedMessages(ids , cleanToken, timeToFix);

    System.out.println("value comming to front-end " + callService);
    
    return callService;
}
}