package what.whatjava.entitys.users;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityMessage;

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
    private String description;

    @Column()
    private String birthday;

    @Column()
    private String password;

    @Column()
    private String token;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private EntityUserNumber number;

    @OneToOne(mappedBy = "userNacionality", cascade = CascadeType.ALL)
    private EntityUserNacionality nacionality;


    //FRIEND SYSTEM
    @OneToMany(mappedBy = "userID", cascade = CascadeType.ALL)
    private List<EntityUserFriend> myUserId;

    @OneToMany(mappedBy = "friendsId", cascade = CascadeType.ALL)
    private List<EntityUserFriend> Friends;


    //CHATS CONTAINERS SYSTEM
    @OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
    private List<EntityChatTable> user1;

    @OneToMany(mappedBy = "user2", cascade = CascadeType.ALL)
    private List<EntityChatTable> user2;
}