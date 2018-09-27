package logic;

import java.sql.Timestamp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.DaoUserData;
import e.StatusResponse;
import remey.jooq.Tables;
import spark.Request;
import structure.StandardResponse;

public class CreateObjectValues {

	ProcessDate pd = new ProcessDate();

	public  String getPullDownYearMonthValues(Request req) throws Exception{
		DaoUserData dao = new DaoUserData();
		String user = req.params(":user");
	try{
		Timestamp createAt = dao.getUserData(user).get(Tables.USERS.CREATE_AT);

		Gson gson = new GsonBuilder().create();
		return new Gson().toJson(
					new StandardResponse(StatusResponse.SUCCESS,
					gson.toJsonTree(pd.getYearMonthListBetweenPastAndNow(createAt)))
				);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return new Gson().toJson(new StandardResponse(StatusResponse.ERROR));
		}
	}
}
