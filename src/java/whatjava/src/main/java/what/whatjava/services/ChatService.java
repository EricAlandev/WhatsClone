package what.whatjava.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.MesssageDTO;
import what.whatjava.dtos.UserResponseDTO.message;
import what.whatjava.dtos.UserResponseDTO.user_1;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityMessage;
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

    //responsable to link chat and message
    @Autowired
    private MessagesChatRepository messagesChatRepository;

    //messages
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TimeService timeService;

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
                if(listFriends.get(y).getFriendsId().getId().equals(usersSearch.get(i).getId())){
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

    //FindMessages
    public List<ChatDTO> findMessages(String id, String cleanToken){

        Claims claims= jwtService.verifyToken(cleanToken);
        Long idLoggedUser = claims.get("id", Long.class);

        Long idOtherUser = Long.parseLong(id);

        //Verify if exist the users;
        EntityUser actualUser = userRepository.findById(idLoggedUser).orElseThrow(() -> new RuntimeException("Actual user dosn't exist"));
        
        EntityUser otherUser = userRepository.findById(idOtherUser).orElseThrow(() -> new RuntimeException("Other user dosn't exist"));

        //verify if they are friends;
        EntityUserFriend friends = userFriendsRepository.findByUserIDAndFriendsId(actualUser, otherUser).orElseThrow(() -> new RuntimeException("The actual user its not friend of the other user"));

        Optional<EntityChatTable> chatTable = chatRepository.findByUser1AndUser2(actualUser, otherUser);

        //message to return (can be fulled or not);
        List<ChatDTO> messagesToReturn = new ArrayList<>();

        if(chatTable.isEmpty()){
            chatTable = chatRepository.findByUser1AndUser2(otherUser , actualUser);
        }
        
        if(!chatTable.isEmpty()){
            EntityChatTable chatTableFound = chatTable.get();

            List<EntityMessagesChat> messagesChat = messagesChatRepository.findByChatTableID(chatTableFound);

            //clean the messages and return
            if(messagesChat.size() > 0){    

               //visualize all of the messages
               for(int i =0; i < messagesChat.size(); i++){
                System.out.println("inside of the for");
                    if(!messagesChat.get(i).getMessageID().getUserID().equals(actualUser)){
                        System.out.println("inside of the if");
                        messagesChat.get(i).getMessageID().setStatus("Visualized");

                        messagesChatRepository.save(messagesChat.get(i));
                    }
               }
               
               return messagesChat.stream()
                .<ChatDTO>map(message -> ChatDTO.builder()
                    .id(message.getId())
                    .idUserMessage(message.getMessageID().getUserID().getId())
                    .name(message.getMessageID().getUserID().getName())
                    .message(message.getMessageID().getMessage())
                    .status(message.getMessageID().getStatus())
                    .time(timeService.TextConvert(message.getMessageID().getTime()))
                    .build()
                ).toList();
            }

            else {
                return messagesToReturn;
            }
        }

        return messagesToReturn;
    }  
    
    //sendMessage
    @Transactional
    public String sendMessage(String id, String cleanToken, String message){

        try {
            Claims claims = jwtService.verifyToken(cleanToken);
            Long idActualUser = claims.get("id", Long.class);
            Long idOtherUser = Long.parseLong(id);

            System.out.println("after the claims" + idActualUser + idOtherUser);
            //find users
            EntityUser actualUser = userRepository.findById(idActualUser).orElseThrow(() -> new RuntimeException("Actual user not found"));

            EntityUser otherUser = userRepository.findById(idOtherUser).orElseThrow(() -> new RuntimeException("other user not found"));
            
            System.out.println("after the creation of users" + actualUser + otherUser);

            //find table or create
            Optional<EntityChatTable> chatTableFound = chatRepository.findByUser1AndUser2(actualUser, otherUser);
            EntityChatTable chatTable = new EntityChatTable();

            //verify if didn't find
            if(chatTableFound.isEmpty()){
                chatTableFound = chatRepository.findByUser1AndUser2(otherUser, actualUser);

                //if the inverted find, its empty again. Create;
                if(chatTableFound.isEmpty()){
                    EntityChatTable newChatTable = new EntityChatTable();
                
                    newChatTable.setUser1(actualUser);
                    newChatTable.setUser2(otherUser);
                    chatRepository.save(newChatTable);
                    chatTable = newChatTable;
                }

                else{
                    chatTable = chatTableFound.get();
                }
            }

            else{
                chatTable = chatTableFound.get();
            }

            System.out.println("after the chatTable" + chatTableFound);

            //create message
            EntityMessage messageValue = new EntityMessage();
            Timestamp timeNow = new java.sql.Timestamp(System.currentTimeMillis());

            messageValue.setMessage(message);
            messageValue.setStatus("not viewed");
            messageValue.setUserID(actualUser);
            messageValue.setTime(timeNow);
            messageRepository.save(messageValue);

            System.out.println("after the messageValue" + messageValue);

            //save in messageChats;
            EntityMessagesChat messagesChat = new EntityMessagesChat();

            messagesChat.setChatTableID(chatTable);
            messagesChat.setMessageID(messageValue);
            messagesChatRepository.save(messagesChat);

            System.out.println("after the messageChat" + messagesChat);

            
            return "200 - message";
        } catch (Exception e) {
            return "fail in the process of send the message";
        }
        
    } 
}
