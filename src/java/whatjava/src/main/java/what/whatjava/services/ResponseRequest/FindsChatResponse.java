package what.whatjava.services.ResponseRequest;

import java.util.ArrayList;
import java.util.List;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.dtos.ChatDTO.MessageDTO;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.services.services.generalFunctions.TimeService;

public class FindsChatResponse {

    public static List<ChatDTO> from(List<EntityChatTable> chats, EntityUser user){

        List<ChatDTO> chatsReturn =  new ArrayList<>();

        for(int i = 0; i < chats.size(); i++){

            EntityChatTable actualChat = chats.get(i);

            //verify who are the other user. If is the user1 or the user2;
            EntityUser otherUser = actualChat.getUser1().equals(user) ? actualChat.getUser2() : actualChat.getUser1();

            //pick the last message to the user;
            MessageDTO lastMessage = new MessageDTO();

            //gonna iterate the 2 for to pick the last message
            for(int y = 0; y < chats.get(i).getMessageChat().size(); y++){
                if(actualChat != null && 
                    (y == (actualChat.getMessageChat().size() - 1))
                ){
    
                    lastMessage.setMessage(actualChat.getMessageChat().get(y).getMessageID().getMessage());
    
                    lastMessage.setStatus(actualChat.getMessageChat().get(y).getMessageID().getStatus());
    
                    lastMessage.setTime(TimeService.TextConvert(actualChat.getMessageChat().get(y).getMessageID().getTime())); 
    
                    break;
                }   
            }

            //gonna build the actual chat with the last message;
            ChatDTO actualChatValues;
    
            actualChatValues = ChatDTO.builder()
            .id(otherUser.getId())
            .name(otherUser.getName())
            .lastMessage(lastMessage)
            .build();
                
            chatsReturn.add(actualChatValues);
        }

        return chatsReturn;
    }
}