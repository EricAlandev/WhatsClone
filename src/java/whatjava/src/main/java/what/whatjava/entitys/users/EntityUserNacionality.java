package what.whatjava.entitys.users;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "user_nacionality") // Good practice to use plural names
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityUserNacionality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String city;

    @Column()
    private String country;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private EntityUser userNacionality;


}