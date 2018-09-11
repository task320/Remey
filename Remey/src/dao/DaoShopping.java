package dao;

import java.sql.Date;
import java.sql.ResultSet;

import entity.ConditionsGetInfo;
import entity.Shopping;
import sql.SqlShoppingData;

public class DaoShopping extends DaoSuper {

	public DaoShopping() throws Exception{
		super();
	}

	public ResultSet InsertShopping(Shopping data) throws Exception{
		try{
			StringBuilder sql = SqlShoppingData.InsertShoppingData();
			pStmt = conn.prepareStatement(sql.toString());

			pStmt.setString(1, data.getUser());
			pStmt.setInt(2, data.getAmount());
			pStmt.setDate(3, (Date) data.getSqlDate());

			ResultSet result = pStmt.executeQuery();

			return result;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(conn != null){
				try{
					conn.close();
				}
				catch(Exception e){
					throw e;
				}
			}
		}
	}

	public ResultSet SelectMonthlyShopping(ConditionsGetInfo data) throws Exception{
		try{
			StringBuilder sql = SqlShoppingData.SelectMonthlyShoppingData();
			pStmt = conn.prepareStatement(sql.toString());

			pStmt.setString(1, data.getUser());
			pStmt.setDate(2, (Date) data.getSqlDate());

			ResultSet result = pStmt.executeQuery();

			return result;
		}
		catch(Exception e){
			Date printDate = (Date) data.getSqlDate();
			System.out.print(printDate.toString());

			throw e;
		}
		finally{
			if(conn != null){
				try{
					conn.close();
				}
				catch(Exception e){
					throw e;
				}
			}
		}
	}

}
