package infrastructure;

import static spark.Spark.*;
import adapters.controller.data.*;

import infrastructure.connection.IDBConnection;
import infrastructure.connection.JooqPostgresqlConnection;
import infrastructure.settings.SettingReader;
import infrastructure.settings.yaml.object.Settings;

public class Entry {
    public static void main(String[] args) {
		Settings settings = null;
		IDBConnection connection = null;

		try {
			settings = SettingReader.getSettings();
			connection = new JooqPostgresqlConnection(settings);
		}catch (Exception e){
			return;
		}

    	options("/*",
    	        (request, response) -> {

    	            String accessControlRequestHeaders = request
    	                    .headers("Access-Control-Request-Headers");
    	            if (accessControlRequestHeaders != null) {
    	                response.header("Access-Control-Allow-Headers",
    	                        accessControlRequestHeaders);
    	            }

    	            String accessControlRequestMethod = request
    	                    .headers("Access-Control-Request-Method");
    	            if (accessControlRequestMethod != null) {
    	                response.header("Access-Control-Allow-Methods",
    	                        accessControlRequestMethod);
    	            }

    	            return "OK";
    	        });

    	after("/api/*",(request, response) -> response.type("application/json"));
    	after("/*",(request, response) -> response	.header("Access-Control-Allow-Origin", "*"));
    	after("/api/get/*",(request, response) -> response	.header("Access-Control-Allow-Methods", "GET"));
    	after("/*",(request, response) -> response	.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"));
    	after("/api/post/*",(request, response) -> response.header("Access-Control-Allow-Methods", "POST, OPTIONS"));

    	//セッション
		before("/*", (req, res) -> {//リダイレクト->ログイン
		});
		
		//ログイン

		//データ
		//取得
		//ユーザデータ
		get("/api/get/data/user", (req, res) -> {

		});
		//サマリー
		get("/api/get/data/summary/top", (req, res) -> {});
		//日データ
		get("/api/get/data/day/:year/:month/:day", (req, res) -> {});
		//月データ
		get("/api/get/data/month/:year/:month", (req, res) -> {});
		//年データ
		get("/api/get/data/year/:year", (req, res) -> {});

		//書込
		//日データ
		post("/api/post/data/day", (req, res) -> {});

		//編集
		//日データ
		put("/api/put/data/day", (req, res) -> {});

		//削除
		//日データ
		delete("/api/delete/data/day", (req, res) -> {});
    }
}