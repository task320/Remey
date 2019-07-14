package sample_code.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Shopping {
	private int amount;
	private Date date;
	private String user;
	private int shopping_id;

	public Shopping(String user, int amount, Date date) throws Exception{
		this.user = user;
		this.amount = amount;
		this.date = date;
	}

	public Shopping(int shopping_id, int amount, Date date) throws Exception{
		this.setShopping_id(shopping_id);
		this.amount = amount;
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
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

	public int getShopping_id() {
		return shopping_id;
	}

	public void setShopping_id(int shopping_id) {
		this.shopping_id = shopping_id;
	}
}
