package what.whatjava.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
    
    public String TextConvert(Timestamp time){

        if(time == null) return "";

        LocalDateTime ldt = time.toLocalDateTime();

        //better formatation
        int hour = ldt.getHour();
        String hourConverted = "" + hour;
        if(hour == 0){
            hourConverted = "0" +  hourConverted;
        }


        return "" + hourConverted + ":" + ldt.getMinute();
    }
}
