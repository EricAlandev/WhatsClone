package what.whatjava.services.ResponseRequest;

import java.util.List;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;

public class SearchFriendsToAddResponse {
    
    public static List<UserResponseDTO> from (List<EntityUser>  findedUsers){
        return findedUsers.stream()
            .map(list -> UserResponseDTO.builder()
                .id(list.getId())
                .name(list.getName())
                .email(list.getEmail())
                .description(list.getDescription())
                .build()
            ).toList();
    }
}
