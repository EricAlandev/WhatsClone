package what.whatjava.entitys.logs;

import java.sql.Timestamp;

import jakarta.persistence.*;
import lombok.*;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.users.EntityUser;

@Entity
@Table(name = "message_log") // Good practice to use plural names
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityMessageLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private EntityMessage messageIdLog;

    @Column(length = 55)
    private String action;

    @Column(length = 55)
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EntityUser userIdMessage;
}
