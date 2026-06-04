package what.whatjava.controllers;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.dtos.TimeToFixDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.dtos.ChatDTO.MessageDTO;
import what.whatjava.dtos.DomainListDTO;
import what.whatjava.resources.ChatResource;
import what.whatjava.services.ResponseRequest.BlockUserResponse;
import what.whatjava.services.ResponseRequest.ClearMessagesResponse;
import what.whatjava.services.ResponseRequest.DeleteMessageResponse;
import what.whatjava.services.ResponseRequest.EditMessageResponse;
import what.whatjava.services.ResponseRequest.FindsChatResponse;
import what.whatjava.services.ResponseRequest.FixedMessageResponse;
import what.whatjava.services.ResponseRequest.PullMessagesResponse;
import what.whatjava.services.ResponseRequest.PullOptionsResponse;
import what.whatjava.services.ResponseRequest.PullUserDataResponse;
import what.whatjava.services.ResponseRequest.SearchFriendsResponse;
import what.whatjava.services.ResponseRequest.SendMessageResponse;
import what.whatjava.services.ResponseRequest.UnBlockUserResponse;
import what.whatjava.services.ResponseRequest.VerifyFixedTimeResponse;
import what.whatjava.services.ResponseRequest.VerifyFixedTimeResponse.ReturnTimeResponse;
import what.whatjava.services.services.ServiceExecute;
import what.whatjava.services.services.Chat.BlockUserService;
import what.whatjava.services.services.Chat.ClearMessageUserService;
import what.whatjava.services.services.Chat.DeleteMessagesService;
import what.whatjava.services.services.Chat.EditMessageService;
import what.whatjava.services.services.Chat.FindChatsService;
import what.whatjava.services.services.Chat.FixedMessageService;
import what.whatjava.services.services.Chat.PullAllOptionsService;
import what.whatjava.services.services.Chat.PullMessagesService;
import what.whatjava.services.services.Chat.PullUserDataService;
import what.whatjava.services.services.Chat.SearchFriendsService;
import what.whatjava.services.services.Chat.SendMessageService;
import what.whatjava.services.services.Chat.UnBlockUserService;
import what.whatjava.services.services.Chat.UnFixMessageService;
import what.whatjava.services.services.Chat.VerifyFixedTimeService;
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
    private PullAllOptionsService pullAllOptionsService;

    @Autowired
    private PullUserDataService pullUserDataService;

    @Autowired
    private SendMessageService sendMessageService; 

    @Autowired
    private BlockUserService blockUserService;

    @Autowired
    private UnBlockUserService unBlockUserService;

    @Autowired
    private EditMessageService editMessageService;

    @Autowired
    private DeleteMessagesService deleteMessagesService;

    @Autowired
    private FixedMessageService fixedMessageService;

    @Autowired
    private VerifyFixedTimeService verifyFixedTimeService;

    @Autowired
    private UnFixMessageService unFixMessageService;

    @Autowired
    ClearMessageUserService clearMessageUserService;


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
    public CompletableFuture<List<DomainListDTO>> pullAllOptions(String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
                pullAllOptionsService, 
                new PullAllOptionsService.InputValues(cleanToken), 
                (output) -> PullOptionsResponse.from(output.getOptions())
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
     public CompletableFuture<UserResponseDTO> pullUserData(String id, String Token) {

        String cleanToken = jwtService.pickTokenFromHeader(Token);

        return ServiceExecute.execute(
            pullUserDataService, 
            new PullUserDataService.InputValues(id, cleanToken), 
            (output) -> PullUserDataResponse.from(output.getFindedUser(), output.isBlocked(), output.isLoggedUserWhoBlock())
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

    @Override
    public CompletableFuture<String> blockUser(String id, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(                           
                blockUserService,
                new BlockUserService.InputValues(id, cleanToken),
                (output) -> BlockUserResponse.from(output.getMessage())
        );
    }

    @Override
    public CompletableFuture<String> unBlockUser(String id, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(                           
                unBlockUserService,
                new UnBlockUserService.InputValues(id, cleanToken),
                (output) -> UnBlockUserResponse.from(output.getMessage())
        );
    }

    @Override
    @PutMapping("/messages/ids")
    public CompletableFuture<String> editMessage(List<Number> ids, String token ,   MesssageDTO message) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            editMessageService, 
            new EditMessageService.InputValues(ids, cleanToken, message.getMessage()),
            (output) -> EditMessageResponse.from(output.getResponse())
        );
    }

    @Override
    @DeleteMapping("/messages/ids")
    public CompletableFuture<String> deleteMessages(List<Number> ids, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            deleteMessagesService, 
            new DeleteMessagesService.InputValues(ids, cleanToken),
            (output) -> DeleteMessageResponse.from(output.getResponse())
        );
    }

    @Override
    @PutMapping("/messages/clear/{id}")
    public CompletableFuture<String> clearMessages(String id, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            clearMessageUserService, 
            new ClearMessageUserService.InputValues(id, cleanToken),
            (output) -> ClearMessagesResponse.from(output.getMessage())
        );
    }

    @Override
    @PutMapping("/messages/fix/ids")
    public CompletableFuture<String> fixMessage(List<Number> ids, String token, TimeToFixDTO timeToFix) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        
        return ServiceExecute.execute(
            fixedMessageService,
             new FixedMessageService.InputValues(ids, cleanToken, timeToFix.getTimeToFix()),
            (output) -> FixedMessageResponse.from(output.getResponse())
        );
    }

    @Override
    @PutMapping("/messages/verify/fix/ids")
    public CompletableFuture<ReturnTimeResponse> verifyFixMessage(List<Number> ids, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        System.out.println("Inside of the controller");
        
        return ServiceExecute.execute(
            verifyFixedTimeService,
             new VerifyFixedTimeService.InputValues(ids, cleanToken),
            (output) -> VerifyFixedTimeResponse.from(output.getResponse(), output.getMessage())
        );
    }

    @Override
    @DeleteMapping("/messages/fix/ids")
    public CompletableFuture<String> unFixMessage(List<Number> ids, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            unFixMessageService,
             new UnFixMessageService.InputValues(ids, cleanToken),
            (output) -> FixedMessageResponse.from(output.getResponse())
        );
    }
}