package what.whatjava.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DomainListDTO {
    private Long id;
    private String nameList;
    private String description;
    private String imageUrl;
    private Long code;
}
