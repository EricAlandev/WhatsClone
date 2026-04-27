package what.whatjava.entitys.chats;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import what.whatjava.entitys.users.EntityUser;

@Entity
@Table(name = "message") 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EntityUser userID;

    @Column
    private String message;

    @Column
    private String status;

    @OneToMany(mappedBy = "messageID", cascade = CascadeType.ALL)
    private List<EntityMessagesChat> messageChat;

}