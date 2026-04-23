package what.whatjava.entitys.chats;
import java.util.List;

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
    private EntityUser user1;

    @ManyToOne
    @JoinColumn(name = "user_2")
    private EntityUser user2;

    @OneToMany(mappedBy = "chatTableID", cascade = CascadeType.ALL)
    private List<EntityMessagesChat> messageChat;


}