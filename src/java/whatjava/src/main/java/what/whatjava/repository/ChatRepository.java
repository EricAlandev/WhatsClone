package what.whatjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.users.EntityUser;

@Repository
public interface ChatRepository extends JpaRepository<EntityChatTable, Long> {

    List<EntityChatTable> findByUser1(EntityUser user);

    Optional<EntityChatTable> findByUser1AndUser2(EntityUser user, EntityUser user2);


    List<EntityChatTable> findByUser2(EntityUser user);

    List<EntityChatTable> findByUser1OrUser2(EntityUser user, EntityUser user2);

    
}

