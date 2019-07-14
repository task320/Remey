package adapters.request.data;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ReqParamsGetDataUser {
    private String usersId;

    public ReqParamsGetDataUser(String reqBodyJson) throws Exception {
        JsonObject jb = new JsonParser().parse(reqBodyJson).getAsJsonObject();
        this.usersId = jb.get("users_id").getAsString();
    }

    public String getUsersId() {
        return this.usersId;
    }

}
