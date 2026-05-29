package what.whatjava.services.ResponseRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PullUserDataRequest {
    
    @NotBlank(message = "id cannot be blank")
    @NotNull(message = "id cannot be null")
    @NotEmpty(message = "id cannot be empty")
    private String id;

    @NotBlank(message = "token cannot be blank")
    @NotNull(message = "token cannot be null")
    @NotEmpty(message = "token cannot be empty")
    private String Token;
}
