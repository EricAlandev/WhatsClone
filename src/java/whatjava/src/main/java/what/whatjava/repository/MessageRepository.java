package what.whatjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.users.EntityUser;


@Repository
public interface MessageRepository extends JpaRepository<EntityMessage, Long> {

    List<EntityMessage> findByUserID(EntityUser userID);

    Optional<EntityMessage> findById(Number id);
}

