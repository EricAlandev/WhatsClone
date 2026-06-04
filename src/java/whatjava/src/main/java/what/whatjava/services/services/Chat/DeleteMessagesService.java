package what.whatjava.services.services.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.Value;
import what.whatjava.entitys.chats.EntityChatVisibleMessages;
import what.whatjava.entitys.chats.EntityMessage;
import what.whatjava.entitys.chats.EntityMessagesChat;
import what.whatjava.entitys.users.EntityUser;
import what.whatjava.repository.MessageRepository;
import what.whatjava.repository.MessagesChatRepository;
import what.whatjava.repository.UserRepository;
import what.whatjava.repository.VisibleMessageRepository;
import what.whatjava.services.services.UseCase;
import what.whatjava.services.services.Jwt.JwtService;

@Service
public class DeleteMessagesService implements UseCase<DeleteMessagesService.InputValues, DeleteMessagesService.OutPutValues> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessagesChatRepository messagesChatRepository;

    @Autowired 
    private VisibleMessageRepository visibleMessageRepository;

    @Autowired
    private JwtService jwtService;

    @Value
    public static class InputValues implements UseCase.InputValues{
        List<Number> ids;
        String token;
    }

    @Value
    public static class OutPutValues implements UseCase.OutPutValues{
        String response;
    }

    @Override
    @Transactional
    public OutPutValues execute(InputValues input){

        String token = input.getToken();
        List<Number> ids = input.getIds();

        Long idUser = jwtService.authentication(token);

        //verify if user exists;
        EntityUser loggedUser = userRepository.findById(idUser).orElseThrow(() -> new RuntimeException("user dosn't exist"));

        //find and delete the messages
        for(int i = 0; i < ids.size(); i++){
            Number actualId = ids.get(i);

            EntityMessage message = messageRepository.findById(actualId).orElseThrow(() -> new RuntimeException("Message not found"));

            EntityMessagesChat chat = messagesChatRepository.findByMessageID(message);

            if(chat != null){
                EntityChatVisibleMessages vM = visibleMessageRepository.findByChatVisibleMessagesAndUserVisibleMessage(chat, loggedUser);

                if(vM != null && vM.isVisible() == true){
                    vM.setVisible(false);
                    visibleMessageRepository.save(vM);
                }
            }
        }

        return new OutPutValues("Messages deleted with sucess!");
    }
}
