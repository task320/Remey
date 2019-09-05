package operator.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ConverterDateAndTime {


    public static Timestamp LocalDateToTimestamp(LocalDate localDate){
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    public static Date LocalDateToDate(LocalDate localDate){
        return Date.valueOf(localDate);
    }
}
