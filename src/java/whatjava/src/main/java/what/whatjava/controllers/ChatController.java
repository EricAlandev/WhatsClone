package what.whatjava.controllers;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.dtos.TimeToFixDTO;
import what.whatjava.dtos.ChatDTO.MessageDTO;
import what.whatjava.resources.ChatResource;
import what.whatjava.services.ResponseRequest.DeleteMessageResponse;
import what.whatjava.services.ResponseRequest.EditMessageResponse;
import what.whatjava.services.ResponseRequest.FindsChatResponse;
import what.whatjava.services.ResponseRequest.FixedMessageResponse;
import what.whatjava.services.ResponseRequest.PullMessagesResponse;
import what.whatjava.services.ResponseRequest.SearchFriendsResponse;
import what.whatjava.services.ResponseRequest.SendMessageResponse;
import what.whatjava.services.services.ServiceExecute;
import what.whatjava.services.services.Chat.DeleteMessagesService;
import what.whatjava.services.services.Chat.EditMessageService;
import what.whatjava.services.services.Chat.FindChatsService;
import what.whatjava.services.services.Chat.FixedMessageService;
import what.whatjava.services.services.Chat.PullMessagesService;
import what.whatjava.services.services.Chat.SearchFriendsService;
import what.whatjava.services.services.Chat.SendMessageService;
import what.whatjava.services.services.Jwt.JwtService;


@RestController 
@RequestMapping("/chat") 
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController implements ChatResource {
    
    @Autowired
    private FindChatsService findChatsService; 

    @Autowired
    private SearchFriendsService searchFriendsService; 

    @Autowired
    private PullMessagesService pullMessagesService; 

    @Autowired
    private SendMessageService sendMessageService; 

    @Autowired
    private EditMessageService editMessageService;

    @Autowired
    private DeleteMessagesService deleteMessagesService;

    @Autowired
    private FixedMessageService fixedMessageService;


    @Autowired
    JwtService jwtService;

    @Override
    public CompletableFuture<List<ChatDTO>> findChats(String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
                findChatsService, 
                new FindChatsService.InputValues(cleanToken), 
                (output) -> FindsChatResponse.from(output.getChats(), output.getUser())
        );
     }

     @Override
     public CompletableFuture<List<ChatDTO>> searchFriendsToChat(String valueSearch ,String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
                searchFriendsService, 
                new SearchFriendsService.InputValues(valueSearch, cleanToken), 
                (output) -> SearchFriendsResponse.from(output.getSearchedFriends())
        );
     }

     @Override
     public CompletableFuture<List<MessageDTO>> pullMessages(String id, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            pullMessagesService, 
            new PullMessagesService.InputValues(id, cleanToken), 
            (output) -> PullMessagesResponse.from(output.getMessages())
        );
     }

     @Override
     public CompletableFuture<String> sendMessage(String id, String token, MesssageDTO message) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(                           
                sendMessageService,
                new SendMessageService.InputValues(id, cleanToken, message),
                (output) -> SendMessageResponse.from(output.getResponseMeessage())
        );
    }

    @PutMapping("/messages/ids")
    public CompletableFuture<String> editMessage(List<Number> ids, String token ,   MesssageDTO message) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            editMessageService, 
            new EditMessageService.InputValues(ids, cleanToken, message.getMessage()),
            (output) -> EditMessageResponse.from(output.getResponse())
        );
    }
    

    @DeleteMapping("/messages/ids")
    public CompletableFuture<String> deleteMessages(List<Number> ids, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            deleteMessagesService, 
            new DeleteMessagesService.InputValues(ids, cleanToken),
            (output) -> DeleteMessageResponse.from(output.getResponse())
        );
    }

    @PutMapping("/messages/fix/ids")
    public CompletableFuture<String> fixMessage(List<Number> ids, String token, TimeToFixDTO timeToFix) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        
        return ServiceExecute.execute(
            fixedMessageService,
             new FixedMessageService.InputValues(ids, cleanToken, timeToFix.getTimeToFix()),
            (output) -> FixedMessageResponse.from(output.getResponse())
        );
    }
}