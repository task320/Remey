package adapters.request.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReqParamsInsertDataDay {
    private int usersId;
    private LocalDate dataDate;
    private long income;
    private long spending;
    private List<String> tags;

    public ReqParamsInsertDataDay(String reqBodyJson) throws Exception {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = jb.get("users_id").getAsInt();
        this.dataDate = LocalDate.parse(jb.get("date").getAsString(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.income = jb.get("income").getAsLong();
        this.spending = jb.get("spending").getAsLong();
        jb.get("tags").getAsJsonArray().forEach(tag -> tags.add(tag.toString()));

    }

    public int getUsersId() {
        return this.usersId;
    }

    public LocalDate getDataDate() {
        return this.dataDate;
    }

    public long getIncome() {
        return this.income;
    }

    public long getSpending() {
        return this.spending;
    }
}