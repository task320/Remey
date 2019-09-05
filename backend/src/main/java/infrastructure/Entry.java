package infrastructure;

import static spark.Spark.*;

import constant.HttpStatus;
import infrastructure.connection.DBConnection;
import infrastructure.connection.JooqPostgresqlConnection;
import infrastructure.settings.SettingReader;
import infrastructure.settings.yaml.object.Settings;

public class Entry {
    public static void main(String[] args) {
		Settings settings = null;

		//設定読み込み
		try {
			settings = SettingReader.getSettings();
		}catch (Exception e){
			return;
		}

        //CORS対策
    	options("/api/*",
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


    	path("/api", () ->{
			before("/*",(req, res) -> 	{
				res.type("application/json");
				res.header("Access-Control-Allow-Origin", "*");
				res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
				res.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			});
		});

    	//セッション確認
		before("/api/*", (req, res) -> {
			//ログイン確認
			if(req.session().attribute("usersId") == null){
				halt(HttpStatus.UNAUTHORIZED);
			}
		});

		//ルート設定
		ApiRoutes.setRoute(settings);
    }
}