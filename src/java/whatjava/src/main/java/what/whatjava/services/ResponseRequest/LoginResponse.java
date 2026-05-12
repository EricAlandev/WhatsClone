package what.whatjava.services.ResponseRequest;

import what.whatjava.dtos.UserResponseDTO;
import what.whatjava.entitys.users.EntityUser;

public class LoginResponse {
    
    public static UserResponseDTO from(EntityUser user){
        return UserResponseDTO.builder()
        .id(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .token(user.getToken())
        .build();
    }
}

