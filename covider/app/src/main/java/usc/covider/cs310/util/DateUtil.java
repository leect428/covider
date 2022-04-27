package usc.covider.cs310.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

//simple class to return date
public class DateUtil {
    public static String getToday(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date today = new Date();
        String dateString = "";
        try {
            Date todayWithZeroTime = formatter.parse(formatter.format(today));
            dateString = formatter.format(todayWithZeroTime);
        } catch(Exception e){
            e.printStackTrace();
        }
        return dateString;
    }
    public static long getTodayEpoch(){
        LocalDate d = LocalDate.now();
        return d.toEpochDay();
    }
}
