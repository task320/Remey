package infrastructure;

import adapters.controller.data.ControllerDataBalance;
import constant.ResponseMessage;
import infrastructure.connection.DBConnection;
import infrastructure.connection.JooqPostgresqlConnection;
import infrastructure.settings.SettingReader;
import infrastructure.settings.yaml.object.Settings;

import static spark.Spark.*;

public class ApiRoutes {
    public static void setRoute(Settings settings) {

        DBConnection connection = null;

        try {
            connection = new JooqPostgresqlConnection(settings);
        }catch (Exception e){
            return;
        }

        ControllerDataBalance  controllerDataBalance = new ControllerDataBalance(connection);
        //ログイン
        //データ
        //取得
        //ユーザデータ
        get("/api/get/user", (req, res) -> {
            return ResponseMessage.EMPTY;
        });
        //トップサマリー
        get("/api/get/summary/top", (req, res) -> {
            return ResponseMessage.EMPTY;
        });
        //日データ
        get("/api/get/balance/day", (req, res) -> {
            String usersId = req.session().attribute("usersId");
            String json = req.body();
            return controllerDataBalance.getBalanceDay(usersId, json);
        });
        //月データ
        get("/api/get/balance/month", (req, res) -> {
            String usersId = req.session().attribute("usersId");
            String json = req.body();
            return controllerDataBalance.getBalanceMonth(usersId, json);
        });
        //年データ
        get("/api/get/balance/year", (req, res) -> {
            String usersId = req.session().attribute("usersId");
            String json = req.body();
            return controllerDataBalance.getBalanceYear(usersId, json);
        });

        //収支データ　追加
        post("/api/post/balance", (req, res) -> {
            String usersId = req.session().attribute("usersId");
            String json = req.body();
            return controllerDataBalance.AddBalance(usersId, json);
        });

        //収支データ 編集
        put("/api/put/balance", (req, res) -> {
            String usersId = req.session().attribute("usersId");
            String json = req.body();
            return controllerDataBalance.UpdateBalance(usersId, json);
        });

        //収支データ 削除
        delete("/api/delete/balance", (req, res) -> {
            String usersId = req.session().attribute("usersId");
            String json = req.body();
            return controllerDataBalance.DeleteBalance(usersId, json);
        });
    }
}
