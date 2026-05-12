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
public class RegisterRequest {

    @NotNull(message = "Name can't be null")
    @NotBlank(message = "Name can't be blank")
    @NotEmpty(message = "Name can't be empty")
    private String name;

    @NotNull(message = "birthday can't be null")
    @NotBlank(message = "birthday can't be blank")
    @NotEmpty(message = "birthday can't be empty")
    private String birthday;

    @NotNull(message = "city can't be null")
    @NotBlank(message = "city can't be blank")
    @NotEmpty(message = "city can't be empty")
    private String city;

    @NotNull(message = "country can't be null")
    @NotBlank(message = "country can't be blank")
    @NotEmpty(message = "country can't be empty")
    private String country;

    @NotNull(message = "ddd can't be null")
    @NotBlank(message = "ddd can't be blank")
    @NotEmpty(message = "ddd can't be empty")
    private String ddd;

    @NotNull(message = "number can't be null")
    @NotBlank(message = "number can't be blank")
    @NotEmpty(message = "number can't be empty")
    private String number;

    @NotNull(message = "password can't be null")
    @NotBlank(message = "password can't be blank")
    @NotEmpty(message = "password can't be empty")
    private String password;

    @NotNull(message = "email can't be null")
    @NotBlank(message = "email can't be blank")
    @NotEmpty(message = "email can't be empty")
    private String email;
}
