package logic;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import dao.DaoUserData;
import remey.jooq.Tables;

public class CreateObjectValues {

	public HashMap<Integer,List<Integer>> getPullDownYearMonthValues(String user) throws Exception{
		DaoUserData dao = new DaoUserData();
		Timestamp createAt = dao.getUserData(user).get(Tables.USERS.CREATE_AT);

		Calendar now = Calendar.getInstance();
		Calendar procCalendar = Calendar.getInstance();
		procCalendar.setTimeInMillis(createAt.getTime());

		HashMap<Integer,List<Integer>> pullDownData = new  HashMap<Integer,List<Integer>>();

		List<Integer> monthList;
		int year;

		year = procCalendar.get(Calendar.YEAR);
		monthList = new ArrayList<Integer>();

		do{
			monthList.add(procCalendar.get(Calendar.MONTH) + 1);

			if(year != procCalendar.get(Calendar.YEAR)){
				pullDownData.put(year, monthList);
				monthList.clear();
				year = procCalendar.get(Calendar.YEAR);
			}

			procCalendar.add(Calendar.MONTH, 1);
		}while(now.get(Calendar.YEAR) > procCalendar.get(Calendar.YEAR)
				|| (now.get(Calendar.YEAR) == procCalendar.get(Calendar.YEAR) && now.get(Calendar.MONTH) >= procCalendar.get(Calendar.MONTH)));

		if(monthList.size() > 0){
			pullDownData.put(year, monthList);
		}


		return pullDownData;
	}

}
