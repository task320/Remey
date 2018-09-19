package main;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import e.StatusResponse;
import entity.ConditionsGetInfo;
import entity.Shopping;
import logic.CreateObjectValues;
import logic.ProcessShoppingData;
import structure.StandardResponse;

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


    	//今日のデータを取得
        get("/remey/get/today/:user", (req, res) -> {

        	/*List shopping =
        	return  new Gson().toJson(
        		      new StandardResponse(StatusResponse.SUCCESS,new Gson()
        		    	        .toJsonTree()));
       		*/
        	return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS));
        });

        //月でデータを取得
        get("/remey/get/month/:year/:month/:user", (req, res) -> {
        	String year = req.params(":year");
        	String month = req.params(":month");

    		ConditionsGetInfo conditions = new ConditionsGetInfo("",null);
    		try{
    			conditions.setData(year + "/" + month + "/01");
    			conditions.setUser("task_mon"); //debug_code

        		List<Shopping> ShoppingList = psd.GetMonthlyShopping(conditions);

        		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
        		return new Gson().toJson(
        					new StandardResponse(StatusResponse.SUCCESS,
        					gson.toJsonTree(ShoppingList))
        				);
    		}
    		catch(Exception e){
    			System.out.println(e.getMessage());
    			e.printStackTrace();
    			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
    		}
        });

        //すべてのデータを取得
        get("/remey/get/all/:user", (req, res) -> {

        	return "";
        });


        //データ登録
    	post("/remey/post/:user", (req, res) -> {
    		Shopping shopping = null;
    		try{
    			Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
    			shopping = gson.fromJson(req.body(), Shopping.class);
    			shopping.setUser("task_mon"); //debug_code

    			int shopping_id = psd.Insert(shopping);
        		if(shopping_id >= 0){
        			shopping.setShopping_id(shopping_id);
        			return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,gson.toJsonTree(shopping)));
        		}else{
        			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
        		}
    		}
    		catch(Exception e){
    			System.out.println(e.getMessage());
    			e.printStackTrace();
    			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
    		}
    	});


    	get("/remey/get/month-list/:user", (req, res) -> {
    		try{
	    		HashMap<Integer,List<Integer>> pullDownYearMonthValues = cov.getPullDownYearMonthValues("task_mon"); //debug_code
	    		return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,new Gson().toJsonTree(pullDownYearMonthValues)));
    		}catch(Exception e){
    			System.out.println(e.getMessage());
    			e.printStackTrace();
    			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
    		}
    	});

    }
}