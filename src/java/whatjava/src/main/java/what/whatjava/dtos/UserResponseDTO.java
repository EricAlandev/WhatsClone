package what.whatjava.dtos;

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
    private String token;
    private NumberDTO number;
    private NacionalityDTO nacionality;

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
}