package what.whatjava.entitys.domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "domain_lists") // Good practice to use plural names
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityDomainLists {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( length = 50)
    private String namelist;

    @Column(unique = true, nullable = false)
    private Long code;
}