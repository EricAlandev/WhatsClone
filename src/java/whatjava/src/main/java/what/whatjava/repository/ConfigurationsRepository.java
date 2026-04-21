package what.whatjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import what.whatjava.entitys.domains.EntityDomainLists;
@Repository

public interface ConfigurationsRepository extends JpaRepository<EntityDomainLists, Long> {
    Optional<EntityDomainLists> findBynamelist(String nameList);
}
