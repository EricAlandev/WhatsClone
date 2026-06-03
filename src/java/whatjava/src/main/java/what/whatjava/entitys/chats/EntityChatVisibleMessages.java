package what.whatjava.entitys.chats;

import jakarta.persistence.*;
import lombok.*;
import what.whatjava.entitys.users.EntityUser;

@Entity
@Table(name = "chat_visible_messages") 
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityChatVisibleMessages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "messages_chat_id")
    private EntityMessagesChat chatVisibleMessages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private EntityUser userVisibleMessage;
    
    @Column()
    private boolean visible;
}