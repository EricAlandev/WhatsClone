package what.whatjava.entitys.chats;
import jakarta.persistence.*;
import lombok.*;
import what.whatjava.entitys.users.EntityUser;

@Entity
@Table(name = "chat_table") 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityChatTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_1")
    private EntityUser user_1;

    @ManyToOne
    @JoinColumn(name = "user_2")
    private EntityUser user_2;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private EntityMessage message;

}