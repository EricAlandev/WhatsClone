package what.whatjava.entitys.users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_friends") 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityUserFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EntityUser userID;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private EntityUser friendsId;

}