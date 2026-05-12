package what.whatjava.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;

import what.whatjava.dtos.SearchDTO;
import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.resources.UserResource;
import what.whatjava.services.ResponseRequest.RegisterRequest;
import what.whatjava.services.ResponseRequest.RegisterResponse;
import what.whatjava.services.services.ServiceExecute;
import what.whatjava.services.services.loginAndRegister.RegisterService;
import what.whatjava.services.services.loginAndRegister.UserService;

public class UserController implements UserResource {

    @Autowired
    private UserService  userService; 
        @Autowired 
    private  RegisterService  registerService;

    @Override
    public CompletableFuture<String> registerUser(RegisterRequest user) {

        return ServiceExecute.execute(
            registerService,
            new RegisterService.InputValues(user),
            (outPut) -> RegisterResponse.from(outPut.getRegisterResponse().getName())
        );
    }

    @Override
    public List<UserResponseDTO> searchUsers(SearchDTO search, String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        List<UserResponseDTO> callService = userService.searchUsers( search.getSearch() , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    }

    @Override
    public String addFriend(Long id, @RequestHeader String token) {

        String cleanToken = (token.startsWith("Bearer ") 
                ? token.substring(7) : token
        );

        String callService = userService.addFriendService(id , cleanToken);

        System.out.println("value comming to front-end " + callService);
        
        return callService;
    } {
        
    }

}
