package what.whatjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.logs.EntityMessageLog;

@Repository
public interface MessageLogRepository extends JpaRepository<EntityMessageLog, Long> {
    
}
