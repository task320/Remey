package main;
import static spark.Spark.*;

import logic.CreateObjectValues;
import logic.ProcessShoppingData;

public class Entry {

	static ProcessShoppingData psd = new ProcessShoppingData();
	static CreateObjectValues cov = new CreateObjectValues();


    public static void main(String[] args) {

    	port(8081);

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

    	after("/remey/*",(request, response) -> response.type("application/json"));
    	after("/remey/*",(request, response) -> response	.header("Access-Control-Allow-Origin", "*"));
    	after("/remey/get/*",(request, response) -> response	.header("Access-Control-Allow-Methods", "GET"));
    	after("/remey/*",(request, response) -> response	.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"));
    	after("/remey/post/*",(request, response) -> response.header("Access-Control-Allow-Methods", "POST, OPTIONS"));

        //月でデータを取得
        get("/remey/get/month/:year/:month/:user", (req, res) -> { return psd.getMonthlyShopping(req); });

        //ページロード時に必要なデータを取得
        get("/remey/get/pull-down-year-month-values/:user", (req, res) -> { return cov.getPullDownYearMonthValues(req); });

        //データ登録
    	post("/remey/post/:user", (req, res) -> { return psd.insert(req); });

    }
}