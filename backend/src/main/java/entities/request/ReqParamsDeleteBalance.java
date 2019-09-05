package entities.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReqParamsDeleteDataDay {
    private int usersId;
    private int balanceId;

    public ReqParamsDeleteDataDay(String reqBodyJson) {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        usersId = jb.get("users_id").getAsInt();
        balanceId = jb.get("balance_id").getAsInt();

    }

    public int getUsersId() {
        return this.usersId;
    }

    public int getBalanceId() {
        return this.balanceId;
    }
}
