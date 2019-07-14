package sample_code.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConditionsGetInfo {
	private Date date;
	private String user;

	public ConditionsGetInfo(String user, Date date) throws Exception{
		this.user = user;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}
	public java.sql.Date getSqlDate() {
		return new java.sql.Date(date.getTime());
	}

	public void setData(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setData(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try{
			this.date = sdf.parse(date);
		}
		catch(ParseException e){
			throw e;
		}
	}
}
