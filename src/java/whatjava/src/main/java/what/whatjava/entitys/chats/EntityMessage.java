package what.whatjava.entitys.chats;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import what.whatjava.entitys.logs.EntityMessageLog;
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

    @Column
    private Timestamp time;

    @Column
    private boolean edited;

    @Column
    private boolean fixed;

    @Column
    private Timestamp end_fixed;

    @OneToMany(mappedBy = "messageID", cascade = CascadeType.ALL)
    private List<EntityMessagesChat> messageChat;

    @OneToMany(mappedBy = "messageIdLog", cascade = CascadeType.ALL)
    private List<EntityMessageLog> messagesLog;

}