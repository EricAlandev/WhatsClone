package what.whatjava.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.resources.UserResource;
import what.whatjava.services.ResponseRequest.AddFriendResponse;
import what.whatjava.services.ResponseRequest.RegisterRequest;
import what.whatjava.services.ResponseRequest.RegisterResponse;
import what.whatjava.services.ResponseRequest.SearchFriendsToAddResponse;
import what.whatjava.services.ResponseRequest.SearchRequest;
import what.whatjava.services.services.ServiceExecute;
import what.whatjava.services.services.Jwt.JwtService;
import what.whatjava.services.services.loginAndRegister.RegisterService;
import what.whatjava.services.services.users.AddFriendService;
import what.whatjava.services.services.users.SearchUserService;

@RestController
@CrossOrigin
public class UserController implements UserResource {
    @Autowired 
    private  RegisterService  registerService;

    @Autowired
    private SearchUserService searchUserService;

    @Autowired
    private AddFriendService addFriendService; 

    @Autowired
    private JwtService jwtService;

    @Override
    public CompletableFuture<String> registerUser(RegisterRequest user) {

        return ServiceExecute.execute(
            registerService,
            new RegisterService.InputValues(user),
            (outPut) -> RegisterResponse.from(outPut.getRegisterResponse().getName())
        );
    }

    @Override
    public CompletableFuture<List<UserResponseDTO>> searchUsers(SearchRequest search, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);

        return ServiceExecute.execute(
            searchUserService, 
            new SearchUserService.InputValues(search, cleanToken), 
            (out) -> SearchFriendsToAddResponse.from(out.getSearchResponse())
        );
    }

    @Override
    public CompletableFuture<String> addFriend(Long id, String token) {

        String cleanToken = jwtService.pickTokenFromHeader(token);
        

        return ServiceExecute.execute(
            addFriendService, 
            new AddFriendService.InputValues(id, cleanToken), 
            (output) -> AddFriendResponse.from(output.getUserToAdd(), output.getUserAdded())
        );
    } 
}
