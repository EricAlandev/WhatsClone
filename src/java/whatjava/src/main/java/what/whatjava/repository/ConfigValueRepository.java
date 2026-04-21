package what.whatjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.domains.EntityDomainListsValues;

@Repository
public interface ConfigValueRepository extends JpaRepository<EntityDomainListsValues, Long> {
    List<EntityDomainListsValues> findByCode(Long codeNumber);
}
