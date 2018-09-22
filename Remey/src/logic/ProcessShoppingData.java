package logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DaoShopping;
import e.StatusResponse;
import entity.ConditionsGetInfo;
import entity.Shopping;
import remey.jooq.tables.records.ShoppingRecord;
import spark.Request;
import structure.StandardResponse;

public class ProcessShoppingData {

	public String insert(Request req){
		Shopping shopping = null;
		try{
			Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
			shopping = gson.fromJson(req.body(), Shopping.class);
			shopping.setUser("task_mon"); //debug_code

			DaoShopping ds = new DaoShopping();
			ShoppingRecord result = ds.InsertShopping(shopping);

    		shopping.setShopping_id(result.getId());
    		return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,gson.toJsonTree(shopping)));
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
		}
	}

	public String getMonthlyShopping(Request req){
    	String year = req.params(":year");
    	String month = req.params(":month");

		List<Shopping> shoppingList = new ArrayList<Shopping>();

		try{
			ConditionsGetInfo conditions = new ConditionsGetInfo("",null);
			conditions.setData(year + "/" + month + "/01");
			conditions.setUser("task_mon"); //debug_code

			DaoShopping ds = new DaoShopping();
			ResultSet result = ds.SelectMonthlyShopping(conditions);



			while(result.next()){
				int shopping_id = result.getInt("id");
				int amount = result.getInt("amount");
				Date shoppint_at = result.getDate("shopping_at");

				Shopping shopping = new Shopping(shopping_id, amount, shoppint_at);

				shoppingList.add(shopping);
			}


    		Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
    		return new Gson().toJson(
    					new StandardResponse(StatusResponse.SUCCESS,
    					gson.toJsonTree(shoppingList))
    				);

		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
		}
	}


}
