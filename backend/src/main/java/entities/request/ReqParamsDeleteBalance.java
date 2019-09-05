package entities.request;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReqParamsDeleteBalance implements IRequestParameters{
    private String usersId;
    private int balanceId;

    public ReqParamsDeleteBalance(String usersId, String reqBodyJson) {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = usersId;
        balanceId = jb.get("balance_id").getAsInt();

    }

    public String getUsersId() {
        return this.usersId;
    }

    public int getBalanceId() {
        return this.balanceId;
    }

    /**
     * requestパラメータをチェック
     * @return
     */
    public boolean validation(){
        return true;
    }
}
