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
public class LoginRequest {
    
    @NotNull(message = "Email can't be null")
    @NotBlank(message = "Email can't be blank")
    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be blank")
    @NotEmpty(message = "password can't be empty")
    private String password;
}
