package what.whatjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.chats.EntityMessage;

@Repository
public interface MessageRepository extends JpaRepository<EntityMessage, Long> {
}

