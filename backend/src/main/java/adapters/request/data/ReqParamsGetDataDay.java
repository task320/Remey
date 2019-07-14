package adapters.request.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import operator.converter.ConverterDateAndTime;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReqParamsGetDataDay {
    private int usersId;
    private LocalDate dataDate;

    public ReqParamsGetDataDay(String reqBodyJson) throws Exception {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = jb.get("users_id").getAsInt();
        this.dataDate = LocalDate.parse(jb.get("date").getAsString(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public int getUsersId() {
        return this.usersId;
    }

    public LocalDate getDataDate() {
        return this.dataDate;
    }

    public Timestamp getDataDateAtTimestamp(){
        return ConverterDateAndTime.LocalDateToTimestamp(dataDate);
    }

    public Timestamp getDataDatePlusADayAtTimestamp(){
        return ConverterDateAndTime.LocalDateToTimestamp(dataDate.plusDays(1L));
    }
}
