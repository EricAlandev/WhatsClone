package what.whatjava.services;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class TimeService {
    
    public String TextConvert(Timestamp time){

        if(time == null) return "";

        LocalDateTime ldt = time.toLocalDateTime();

        String month;

        switch (ldt.getMonthValue()) {
            case 1:  month = "jan"; break;
            case 2:  month = "feb"; break;
            case 3:  month = "marc"; break;
            case 4:  month = "apri"; break;
            case 5:  month = "may"; break;
            case 6:  month = "june"; break;
            case 7:  month = "july"; break;
            case 8:  month = "augu"; break;
            case 9:  month = "sept"; break;
            case 10: month = "octu"; break;
            case 11: month = "nov"; break;
            case 12: month = "december"; break;
            default: month = "unknown"; break;
        }

        return "" + month + "    " + ldt.getDayOfMonth() + "- " + ldt.getHour() + ":" + ldt.getMinute();
    }
}
