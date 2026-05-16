package what.whatjava.services.services.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.Value;
import what.whatjava.dtos.ChatDTO;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.entitys.users.EntityUserFriend;
import what.whatjava.repository.UserFriendsRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class SearchFriendsService implements UseCase<SearchFriendsService.InputValues, SearchFriendsService.OutPutValues> {
    
    @Autowired
    JwtService jwtService;

    @Autowired
    private UserFriendsRepository userFriendsRepository;

    @Autowired
    private UserRepository userRepository;

    @Value
    public static class InputValues implements UseCase.InputValues{
        String search;
        String token;
    }

    @Value    
    public static class OutPutValues implements UseCase.OutPutValues{
        List<EntityUser> searchedFriends;
    }

    public OutPutValues execute (InputValues input){

        String token = input.getToken();
        String search = input.getSearch();
        
        //verify token;
        Claims claims = jwtService.verifyToken(token);
        Long idUser = claims.get("id", Long.class);

        //gonna have the friends of the user;
        List<EntityUser> searchedFriends = new ArrayList<>();
        
        EntityUser user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User dosn't exist"));

        //findSimilars
        List<EntityUser> usersSearch = userRepository.findByNameContainingIgnoreCase(search);

        if(usersSearch.size() == 0){
            return new OutPutValues(searchedFriends);
        }

        //find the friends of the actual user;
        List<EntityUserFriend> listFriends = userFriendsRepository.findByUserID(user);

        if(listFriends.size() == 0){
            return new OutPutValues(searchedFriends);
        }

        //verify if someFriend match with the search;
        for(int i = 0; i < usersSearch.size(); i++){
            for(int y = 0; y < listFriends.size(); y++){
                if(listFriends.get(y).getFriendsId().getId().equals(usersSearch.get(i).getId())){
                    searchedFriends.add(usersSearch.get(i));
                    break;
                }
            }
        }

        return new OutPutValues(searchedFriends);
    }
}
