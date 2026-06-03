package what.whatjava.entitys.logs;
import java.sql.Timestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import what.whatjava.entitys.users.EntityUser;

@Entity
@Table(name = "blockedLog")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityBlockedLog{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userwhofetch")
    private EntityUser userWhoFetch;

    @ManyToOne
    @JoinColumn(name = "affecteduser")
    private EntityUser affectedUser;
    
    @Column
    private Timestamp time;

    @Column
    private String action;
}