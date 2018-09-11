package logic;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.DaoShopping;
import entity.ConditionsGetInfo;
import entity.Shopping;

public class ProcessShoppingData {

	public int Insert(Shopping data){
		try{
			DaoShopping ds = new DaoShopping();
			ResultSet result = ds.InsertShopping(data);

			result.next();
			return result.getInt("id");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return -1;
		}
	}

	public List<Shopping> GetMonthlyShopping(ConditionsGetInfo data){
		try{
			DaoShopping ds = new DaoShopping();
			ResultSet result = ds.SelectMonthlyShopping(data);

			List<Shopping> shoppingList = new ArrayList<Shopping>();

			while(result.next()){
				int shopping_id = result.getInt("id");
				int amount = result.getInt("amount");
				Date shoppint_at = result.getDate("shopping_at");

				Shopping shopping = new Shopping(shopping_id, amount, shoppint_at);

				shoppingList.add(shopping);
			}

			return shoppingList;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}


}
