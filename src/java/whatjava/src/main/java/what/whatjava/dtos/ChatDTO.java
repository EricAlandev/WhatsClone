package what.whatjava.dtos;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDTO {
    private Long id;
    private Long idUserMessage;
    private String profilePicture;
    private String name;
    private String message;
    private String status;
    private boolean edited;
    private String time;

}
