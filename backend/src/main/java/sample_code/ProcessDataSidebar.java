package sample_code;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DaoUserData;
import sample_code.model.StandardResponse;
import constant.StatusResponse;
import remey.jooq.Tables;
import spark.Request;

import java.sql.Timestamp;

public class ProcessDataSidebar {

	public String getDataSideberCalendar(Request req){
		DaoUserData dao = new DaoUserData();
		String user = req.session().attribute("id");
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
