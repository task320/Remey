package sample_code;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProcessDate {

	public List<String> getYearMonthListBetweenPastAndNow(Timestamp pastTime){
		Calendar now = Calendar.getInstance();
		Calendar procCalendar = Calendar.getInstance();
		procCalendar.setTimeInMillis(pastTime.getTime());

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");

		List<String> yearMonthList= new ArrayList<String>();

		do{
			yearMonthList.add(format.format(procCalendar.getTime()));
			procCalendar.add(Calendar.MONTH, 1);
		}while(now.get(Calendar.YEAR) > procCalendar.get(Calendar.YEAR)
				|| (now.get(Calendar.YEAR) == procCalendar.get(Calendar.YEAR) && now.get(Calendar.MONTH) >= procCalendar.get(Calendar.MONTH)));

		return yearMonthList;
	}
}
