package logic;

import java.sql.Timestamp;
import java.util.List;

import dao.DaoUserData;
import remey.jooq.Tables;

public class CreateObjectValues {

	ProcessDate pd = new ProcessDate();

	public  List<String> getPullDownYearMonthValues(String user) throws Exception{
		DaoUserData dao = new DaoUserData();
		Timestamp createAt = dao.getUserData(user).get(Tables.USERS.CREATE_AT);

		return pd.getYearMonthListBetweenPastAndNow(createAt);

	}

}
