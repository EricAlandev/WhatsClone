package what.whatjava.entitys.users;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_number") // Good practice to use plural names
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityUserNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2)
    private String ddd;

    @Column(length = 9)
    private String number;
    
    @OneToOne()
    @JoinColumn(name = "user_id")
    private EntityUser user;
}
