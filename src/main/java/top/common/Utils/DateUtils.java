package top.common.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

//    private static SimpleDateFormat Timesdf = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat Datesdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Integer getDateOfWeek(){
        Calendar calendar = Calendar.getInstance();
        Integer dateOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dateOfWeek <= 0){
            dateOfWeek = 7;
        }
        return dateOfWeek;
    }

    public static Integer getDateOfMonth(){
        Calendar calendar = Calendar.getInstance();
        Integer dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dateOfMonth;
    }

    public static Integer getMonthOfYear(){
        Calendar calendar = Calendar.getInstance();
        Integer monthOfYear = calendar.get(Calendar.MONTH) + 1;
        return monthOfYear;
    }

    public static String getDates(){
        Date dates = new Date();
        return Datesdf.format(dates);
    }

    public static Date getTime(){
        return new Date();
    }

    public static String getTimeByStr(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
