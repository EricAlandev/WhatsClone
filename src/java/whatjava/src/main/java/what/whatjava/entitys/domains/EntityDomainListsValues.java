package what.whatjava.entitys.domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "domain_lists_value") // Good practice to use plural names
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class EntityDomainListsValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( length = 50)
    private String namelist;

    @Column(length = 120)
    private String description;

    @Column(length = 60)
    private String image_url;

    @Column()
    private Long code;
}