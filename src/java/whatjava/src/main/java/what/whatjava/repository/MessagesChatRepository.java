package what.whatjava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityMessagesChat;

@Repository
public interface MessagesChatRepository extends JpaRepository<EntityMessagesChat, Long> {

    List<EntityMessagesChat> findByChatTableID(EntityChatTable chatTableID);
}

