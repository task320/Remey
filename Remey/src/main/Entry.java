package main;
import static spark.Spark.*;

import com.google.gson.Gson;

import enums.StatusResponse;
import logic.CreateObjectValues;
import logic.OutputPageData;
import logic.ProcessShoppingData;
import logic.login.OAuth2Google;
import logic.login.ProcessLogin;
import structure.StandardResponse;

public class Entry {

	static ProcessShoppingData psd = new ProcessShoppingData();
	static CreateObjectValues cov = new CreateObjectValues();

    public static void main(String[] args) {

    	port(8081);

    	staticFiles.location("/asset");

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

    	after("/remey/api/*",(request, response) -> response.type("application/json"));
    	after("/remey/*",(request, response) -> response	.header("Access-Control-Allow-Origin", "*"));
    	after("/remey/api/get/*",(request, response) -> response	.header("Access-Control-Allow-Methods", "GET"));
    	after("/remey/*",(request, response) -> response	.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept"));
    	after("/remey/api/post/*",(request, response) -> response.header("Access-Control-Allow-Methods", "POST, OPTIONS"));

    	//ログインページ
    	get("/remey", (req, res) -> {
    		try{
    			return OutputPageData.outputLoginPage();
    		}catch(Exception e){
    			e.printStackTrace();
    			halt(500,"Process Error...");
    			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
    		}
    		});

    	//セッションにIDが格納されているか
    	before("/remey/main", (req, res) -> {
    		try{
	    		if(!ProcessLogin.isAuthorize(req, res)){
	    			res.redirect("/remey");
	    		}
    		}catch(Exception e){
    			halt(500,"Process Error...");
    		}
    	});
    	//メインページ
    	get("/remey/main", (req, res) -> {
    		try{
    			return OutputPageData.outputMainPage();
    		}catch(Exception e){
    			e.printStackTrace();
    			halt(500,"Process Error...");
    			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
    		}

    	});

        //月でデータを取得
    	get("/remey/api/get/month/:year/:month", (req, res) -> { return psd.getMonthlyShopping(req);});

        //ページロード時に必要なデータを取得
        get("/remey/api/get/pull-down-year-month-values", (req, res) -> { return cov.getPullDownYearMonthValues(req); });

    	//Google認証
    	get("/remey/auth_google", (req, res) -> {return OAuth2Google.oath2Redirect(req, res);});

    	//Googleログイン処理
    	get("/remey/auth_google_callback", (req, res)->{return ProcessLogin.loginByGoogleInfo(req, res);});

        //データ登録
    	post("/remey/api/post/:user", (req, res) -> { return psd.insert(req); });

    }
}