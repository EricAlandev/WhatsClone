package what.whatjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import what.whatjava.entitys.chats.EntityChatVisibleMessages;
import what.whatjava.entitys.chats.EntityMessagesChat;

public interface VisibleMessageRepository extends JpaRepository<EntityChatVisibleMessages, Long> {
    
    Optional<EntityChatVisibleMessages> findByChatVisibleMessages(EntityMessagesChat messagesChat );
}
