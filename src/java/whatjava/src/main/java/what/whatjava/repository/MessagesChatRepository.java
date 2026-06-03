package what.whatjava.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import what.whatjava.entitys.chats.EntityChatTable;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.chats.EntityMessagesChat;

@Repository
public interface MessagesChatRepository extends JpaRepository<EntityMessagesChat, Long> {

    //Pageable its a prop that define how mutch values do u wanna pull;
    List<EntityMessagesChat> findByChatTableID(EntityChatTable chatTableIDm, org.springframework.data.domain.Pageable pageable);

     List<EntityMessagesChat> findByChatTableID(EntityChatTable chatTableID);

     Optional<EntityMessagesChat> findByChatTableIDAndMessageID(EntityChatTable chatTableIDm, EntityMessage message);


    
}

