package adapters.request.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReqParamsDeleteDataDay {
    private String usersId;
    private String balanceId;

    public ReqParamsDeleteDataDay(String reqBodyJson) {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        usersId = jb.get("users_id").getAsString();
        balanceId = jb.get("balance_id").getAsString();

    }

    public String getUsersId() {
        return this.usersId;
    }

    public String getBalanceId() {
        return this.balanceId;
    }
}
