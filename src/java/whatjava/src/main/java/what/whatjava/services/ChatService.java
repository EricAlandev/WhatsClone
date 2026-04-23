package what.whatjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.UserResponseDTO.message;
import what.whatjava.dtos.UserResponseDTO.user_1;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityMessagesChat;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.entitys.users.EntityUserFriend;
import what.whatjava.repository.ChatRepository;
import what.whatjava.repository.MessageRepository;
import what.whatjava.repository.MessagesChatRepository;
import what.whatjava.repository.UserFriendsRepository;
import what.whatjava.repository.UserRepository;


@Service
public class ChatService {

    @Autowired
    private UserFriendsRepository userFriendsRepository;

    @Autowired
    private UserRepository userRepository;

    //responsable to link the user_1 to user_2
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private JwtService jwtService;

    public List<ChatDTO> searchFriendsService(String search, String token){

        //verify token;
        Claims claims = jwtService.verifyToken(token);
        Long idUser = claims.get("id", Long.class);

        //GENERIC RETURN - IF NOT FOUND THE RESULT, FUNCTION GONNA RETURN A EMPTY LIST;
        List<ChatDTO> emptyList = new ArrayList<>();

        //gonna have the friends of the user;
        List<EntityUser> searchedFriends = new ArrayList<>();
        
        Optional<EntityUser> notVerifyedUser = userRepository.findById(idUser);

        if(notVerifyedUser == null || notVerifyedUser.isEmpty()){
            throw new RuntimeException("User dosn't exist");
        }   

        EntityUser user = notVerifyedUser.get();

        //findSimilars
        List<EntityUser> usersSearch = userRepository.findByNameContainingIgnoreCase(search);

        if(usersSearch.size() == 0){
            return emptyList;
        }

        //find the friends of the actual user;
        List<EntityUserFriend> listFriends = userFriendsRepository.findByUserID(user);

        if(listFriends.size() == 0){
            return emptyList;
        }

        //verify if someFriend beat with the search;
        for(int i = 0; i < usersSearch.size(); i++){
            for(int y = 0; y < listFriends.size(); y++){
                if(listFriends.get(y).getFriends_id().getId().equals(usersSearch.get(i).getId())){
                    searchedFriends.add(usersSearch.get(i));
                    System.out.println("id for" + i);
                    break;
                }
            }
        }

        if(searchedFriends.size() > 0){
            return searchedFriends.stream()
            .map(friend -> ChatDTO.builder()
                .id(friend.getId())
                .name(friend.getName())
                .build()
        ).toList();
        }

        else{
            return emptyList;
        }
        
    }

    public List<ChatDTO> findAllChats(String token){

        //verify token;
        Claims claims = jwtService.verifyToken(token);
        Long idUser = claims.get("id", Long.class);

        Optional<EntityUser> notVerifyedUser = userRepository.findById(idUser);

        if(notVerifyedUser == null || notVerifyedUser.isEmpty()){
            throw new RuntimeException("User dosn't exist");
        }

        EntityUser verifyedUser = notVerifyedUser.get();

        //Gonna pick the table chat_table(Prop of user_1 and user_2);
  
        List<EntityChatTable> user_1 = chatRepository.findByUser1(verifyedUser);

        if(user_1.size() == 0){
            List<ChatDTO> chatsReturnEmpty = new ArrayList<>();
            return chatsReturnEmpty;
        } 

        return user_1.stream()
            .<ChatDTO>map(friend -> ChatDTO.builder()
            .id(friend.getUser1().getId())
            .name(friend.getUser1().getName())
            .build()
            ).toList();
    
    }
}
