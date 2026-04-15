package what.whatjava.entitys.users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // Good practice to use plural names
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( length = 55)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column()
    private String birthday;

    @Column()
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private EntityUserNumber number;

    @OneToOne(mappedBy = "userNacionality", cascade = CascadeType.ALL)
    private EntityUserNacionality nacionality;


}