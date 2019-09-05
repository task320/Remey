package entities.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import operator.converter.ConverterDateAndTime;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReqParamsGetBalanceMonth implements IRequestParameters{
    private String usersId;
    private LocalDate dataDate;

    public ReqParamsGetBalanceMonth(String usersId, String reqBodyJson) throws Exception {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = usersId;
        dataDate = LocalDate.parse(jb.get("date").getAsString(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public String getUsersId() {
        return this.usersId;
    }

    public LocalDate getDataDate() {
        return this.dataDate;
    }

    public Date getDataDateAtDate(){
        return ConverterDateAndTime.LocalDateToDate(dataDate);
    }

    public Date getDataDatePlusAMonthAtDate(){
        return ConverterDateAndTime.LocalDateToDate(dataDate.plusMonths(1L));
    }

    /**
     * requestパラメータをチェック
     * @return
     */
    public boolean validation(){
        return true;
    }
}
