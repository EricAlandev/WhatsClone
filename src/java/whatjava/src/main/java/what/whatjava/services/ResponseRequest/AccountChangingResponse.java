package what.whatjava.services.ResponseRequest;

import java.util.List;

public class AccountChangingResponse {
    
    public static String from(String message, List<String> updatedValues){

        if(updatedValues.size() > 0){
            for(int i = 0; i < updatedValues.size(); i++){
                if(i == updatedValues.size()){
                    message = message + updatedValues.get(i);
                }

                else{
                    message = message + updatedValues.get(i) + " , ";
                }
            }
        }

        return message;
    }
}
