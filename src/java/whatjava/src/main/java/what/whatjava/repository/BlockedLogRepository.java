package what.whatjava.repository;

import org.springframework.stereotype.Repository;

import what.whatjava.entitys.logs.EntityBlockedLog;
import what.whatjava.entitys.users.EntityUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface BlockedLogRepository extends JpaRepository<EntityBlockedLog, Long>{
    
    @Query("Select b from EntityBlockedLog b where b.affectedUser = :blockedUser AND b.userWhoFetch = :userWhoBlocked Order by time desc LIMIT 1")
    Optional<EntityBlockedLog> findLatestBlockByUser(EntityUser userWhoBlocked, EntityUser blockedUser);
}
