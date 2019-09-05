package entities.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ReqParamsAddBalance implements IRequestParameters {
    private int balanceId;
    private String usersId;
    private LocalDate dataDate;
    private long income;
    private long spending;
    private int version;

    private List<String> tags;

    public ReqParamsAddBalance(String usersId, String reqBodyJson) throws Exception {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = usersId;
        this.dataDate = LocalDate.parse(jb.get("date").getAsString(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.income = jb.get("income").getAsLong();
        this.spending = jb.get("spending").getAsLong();
        jb.get("tags").getAsJsonArray().forEach(tag -> tags.add(tag.toString()));

    }

    public String getUsersId() {
        return usersId;
    }

    public LocalDate getDataDate() {
        return dataDate;
    }

    public long getIncome() {
        return income;
    }

    public long getSpending() {
        return spending;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    /**
     * requestパラメータをチェック
     * @return
     */
    public boolean validation(){
        return true;
    }
}
