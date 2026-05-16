package what.whatjava.services.ResponseRequest;

import java.util.List;

import what.whatjava.dtos.ChatDTO.MessageDTO;
import what.whatjava.entitys.chats.EntityMessagesChat;
import what.whatjava.services.services.generalFunctions.TimeService;

public class PullMessagesResponse {
    
    public static List<MessageDTO> from(List<EntityMessagesChat> messagesChat){
        return messagesChat.stream()
                .<MessageDTO>map(message -> MessageDTO.builder()
                    .id(message.getId())
                    .idUserMessage(message.getMessageID().getUserID().getId())
                    .name(message.getMessageID().getUserID().getName())
                    .message(message.getMessageID().getMessage())
                    .status(message.getMessageID().getStatus())
                    .edited(message.getMessageID().isEdited())//for primitive values like boolean. I need to use is
                    .time(TimeService.TextConvert(message.getMessageID().getTime()))
                    .fixed(message.getMessageID().isFixed())
                    .build()
                ).toList();
    }
}
