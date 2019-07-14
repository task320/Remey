package adapters.request.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import operator.converter.ConverterDateAndTime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ReqParamsGetDataYear {
    private int usersId;
    private LocalDate dataDate;

    public ReqParamsGetDataYear(String reqBodyJson) throws Exception {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        usersId = jb.get("users_id").getAsInt();
        dataDate = LocalDate.parse(jb.get("date").getAsString(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public int getUsersId() {
        return usersId;
    }

    public LocalDate getDataDate() {
        return dataDate;
    }

    public Timestamp getDataDateAtTimestamp(){
        return ConverterDateAndTime.LocalDateToTimestamp(dataDate);
    }

    public Timestamp getDataDatePlusAYearAtTimestamp(){
        return ConverterDateAndTime.LocalDateToTimestamp(dataDate.plusYears(1L));
    }
}
