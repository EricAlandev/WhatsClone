package what.whatjava.entitys.chats;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "messages_chat") 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityMessagesChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chat_table_id")
    private EntityChatTable chatTableID;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private EntityMessage messageID;

}