package what.whatjava.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String birthday;
    private String description;
    private String token;
    private NumberDTO number;
    private NacionalityDTO nacionality;
    private List<Friends> friends;
    private List<ChatsContainers> chats;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NumberDTO {
        private String ddd;
        private String number;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NacionalityDTO {
        private String city;
        private String country;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Friends {
        private Long id;
        private String name;
        private String description;
    }

    //Chat container
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatsContainers {
        private Long id;
        private user_1 user_1;
        private user_2 user_2;
        private List<message> messages;
    }
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class user_1 {
        private String name;
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class user_2 {
        private String name;
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class message {
        private String name;
        private String birthday;
    }
}