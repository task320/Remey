package adapters.request.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ReqParamsUpdateDataDay {
    private int usersId;
    private int balanceId;
    private long income;
    private long spending;
    private List<String> tags;

    public ReqParamsUpdateDataDay(String reqBodyJson) {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        usersId = jb.get("users_id").getAsInt();
        balanceId = jb.get("balance_id").getAsInt();
        income = jb.get("income").getAsLong();
        spending = jb.get("spending").getAsLong();
        jb.getAsJsonArray("tags").forEach(tag -> {
            tags.add(tag.toString());
        });
    }

    public int getUsersId() {
        return this.usersId;
    }

    public int getBalanceId() {
        return this.balanceId;
    }

    public long getSpending() {
        return spending;
    }

    public List<String> getTags() {
        return tags;
    }

    public long getIncome() {
        return income;
    }
}
