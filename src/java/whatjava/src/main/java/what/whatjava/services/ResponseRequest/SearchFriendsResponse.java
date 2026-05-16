package what.whatjava.services.ResponseRequest;

import java.util.List;

import what.whatjava.dtos.ChatDTO;
import what.whatjava.entitys.users.EntityUser;

public class SearchFriendsResponse {
    
    public static List<ChatDTO> from(List<EntityUser> searchedFriends){

        return searchedFriends.stream()
            .map(friend -> ChatDTO.builder()
                .id(friend.getId())
                .name(friend.getName())
                .build()
        ).toList();
    }
}
