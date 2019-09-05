package entities.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

public class ReqParamsUpdateBalance implements IRequestParameters{
    private String usersId;
    private int balanceId;
    private long income;
    private long spending;
    private List<String> tags;
    private int version;

    public ReqParamsUpdateBalance(String usersId, String reqBodyJson) {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = usersId;
        balanceId = jb.get("balance_id").getAsInt();
        income = jb.get("income").getAsLong();
        spending = jb.get("spending").getAsLong();
        jb.getAsJsonArray("tags").forEach(tag -> {
            tags.add(tag.toString());
        });
        version = jb.get("version").getAsInt();
    }

    public String getUsersId() {
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

    public int getVersion() {
        return this.version;
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
