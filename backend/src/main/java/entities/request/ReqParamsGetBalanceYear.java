package entities.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import operator.converter.ConverterDateAndTime;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReqParamsGetDataYear implements IRequestParameters{
    private String usersId;
    private LocalDate dataDate;

    public ReqParamsGetDataYear(String usersId, String reqBodyJson) throws Exception {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = usersId;
        dataDate = LocalDate.parse(jb.get("date").getAsString(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public String getUsersId() {
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

    /**
     * requestパラメータをチェック
     * @return
     */
    public boolean validation(){
        return true;
    }
}
