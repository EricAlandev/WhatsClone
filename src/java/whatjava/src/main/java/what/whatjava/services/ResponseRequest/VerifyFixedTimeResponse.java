package what.whatjava.services.ResponseRequest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import what.whatjava.entitys.chats.EntityMessage;

public class VerifyFixedTimeResponse {
    
    public static ReturnTimeResponse from(String response, List<EntityMessage> messages){
        
        String returnMessage = response;

        ReturnTimeResponse objectResponse = new ReturnTimeResponse();

        if(messages.size() > 0){
            for(int i = 0; i < messages.size(); i++){
                Number ActualId = messages.get(i).getId();
                
                returnMessage = returnMessage + ActualId;
            }
        }

        objectResponse.setChangedMessages(messages);
        objectResponse.setMessageOfReturn(returnMessage);

        return objectResponse;
    }
    
    //return Class
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReturnTimeResponse {
        public List<EntityMessage> changedMessages;
        public String messageOfReturn;
    }
}
