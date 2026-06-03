package what.whatjava.services.ResponseRequest;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.dtos.UserResponseDTO.NacionalityDTO;
import what.whatjava.dtos.UserResponseDTO.NumberDTO;
import what.whatjava.entitys.users.EntityUser;

public class PullUserDataResponse {
    
    public static UserResponseDTO from(EntityUser user, boolean blocked, boolean userBlockedChat){

        return UserResponseDTO.builder()
        .name(user.getName())
        .description(user.getDescription())
        .blocked(blocked)
        .userBlockedChat(userBlockedChat)
        .number(NumberDTO.builder().ddd(user.getNumber().getDdd()).number(user.getNumber().getNumber()).build())
        .nacionality(NacionalityDTO.builder().city(user.getNacionality().getCity()).country(user.getNacionality().getCountry()).build())
        .build();
    }
}
