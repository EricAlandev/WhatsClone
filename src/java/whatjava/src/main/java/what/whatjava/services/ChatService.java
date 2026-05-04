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
import what.whatjava.dtos.ChatDTO.MessageDTO;
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

        EntityUser user = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("User dosn't exist"));

        //Gonna pick the table chat_table(Prop of user_1 and user_2);
        return chatRepository.findByUser1OrUser2(user, user).stream().map(chat -> defineUserToDTO(chat, user)).toList();

    }

    private ChatDTO defineUserToDTO(EntityChatTable chat, EntityUser user){

        EntityUser otherUser = chat.getUser1().equals(user) ? chat.getUser2() : chat.getUser1();

        //pick the last message to the user;
        MessageDTO lastMessage = new MessageDTO();
        for(int i = 0; i < chat.getMessageChat().size(); i++){
            if(i + 1 == chat.getMessageChat().size()){
                lastMessage.setMessage(chat.getMessageChat().get(i).getMessageID().getMessage());
                lastMessage.setStatus(chat.getMessageChat().get(i).getMessageID().getStatus());
                lastMessage.setTime(timeService.TextConvert(chat.getMessageChat().get(i).getMessageID().getTime())); 
            }   
        }

        return ChatDTO.builder()
            .id(otherUser.getId())
            .name(otherUser.getName())
            .lastMessage(lastMessage)
            .build();
    }


    //FindMessages
    public List<MessageDTO> findMessages(String id, String cleanToken){

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
        List<MessageDTO> messagesToReturn = new ArrayList<>();

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
                    if(!messagesChat.get(i).getMessageID().getUserID().equals(actualUser)){

                        messagesChat.get(i).getMessageID().setStatus("Visualized");

                        messagesChatRepository.save(messagesChat.get(i));
                    }
               }
               
               return messagesChat.stream()
                .<MessageDTO>map(message -> MessageDTO.builder()
                    .id(message.getId())
                    .idUserMessage(message.getMessageID().getUserID().getId())
                    .name(message.getMessageID().getUserID().getName())
                    .message(message.getMessageID().getMessage())
                    .status(message.getMessageID().getStatus())
                    .edited(message.getMessageID().isEdited())//for primitive values like boolean. I need to use is
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


            //create message
            EntityMessage messageValue = new EntityMessage();
            Timestamp timeNow = new java.sql.Timestamp(System.currentTimeMillis());

            messageValue.setMessage(message);
            messageValue.setStatus("not viewed");
            messageValue.setUserID(actualUser);
            messageValue.setTime(timeNow);
            messageValue.setEdited(false);
            messageRepository.save(messageValue);

            //save in messageChats;
            EntityMessagesChat messagesChat = new EntityMessagesChat();

            messagesChat.setChatTableID(chatTable);
            messagesChat.setMessageID(messageValue);
            messagesChatRepository.save(messagesChat);
 
            return "200 - message";
        } catch (Exception e) {
            return "fail in the process of send the message";
        }
        
    } 
    @Transactional
    public String alterMessage(List<Number> ids, String token, String message){
        Claims claim = jwtService.verifyToken(token);
        Long id = claim.get("id", Long.class);

        userRepository.findById(id).orElseThrow(() -> new RuntimeException("User dosn't exist"));

        Number idMessage = ids.get(0);

        EntityMessage messageDB = messageRepository.findById(idMessage).orElseThrow(() -> new RuntimeException("message dosn't exist"));

        messageDB.setMessage(message);
        messageDB.setEdited(true);

        Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());

        messageDB.setTime(now);
        messageRepository.save(messageDB);

        return  "";
    }


    @Transactional
    public String deleteMessages(List<Number> ids, String token){
        Claims claims = jwtService.verifyToken(token);
        Long idUser = claims.get("id", Long.class);

        //verify if user exists;
        userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("user dosn't exist"));

        //find and delete the messages
        for(int i = 0; i < ids.size(); i++){
            Number actualId = ids.get(i);

            EntityMessage message = messageRepository.findById(actualId).orElseThrow(() -> new RuntimeException("Message not found"));

            messageRepository.delete(message);
        }

        return "Messages deleted with sucess!";
    }

}
