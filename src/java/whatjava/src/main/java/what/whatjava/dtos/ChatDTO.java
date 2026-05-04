package what.whatjava.dtos;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO {
    private Long id;
    private Long idUserChat;
    private String profilePicture;
    private String name;
    private MessageDTO lastMessage;

    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class MessageDTO {
        private Long id;
        private Long idUserMessage;
        private String profilePicture;
        private String name;
        private String message;
        private String status;
        private boolean edited;
        private String time;
    }
}
