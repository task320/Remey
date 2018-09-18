package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import entity.ConditionsGetInfo;
import entity.Shopping;
import remey.jooq.Tables;
import remey.jooq.tables.records.ShoppingRecord;
import sql.SqlShoppingData;

public class DaoShopping extends DaoSuper {

	public DaoShopping() throws Exception{
		super();
	}

	public ShoppingRecord InsertShopping(Shopping data) throws Exception{
		try{
			DSLContext create = DSL.using(conn, SQLDialect.POSTGRES);
			 ShoppingRecord result = create
					.insertInto(Tables.SHOPPING)
					.set(Tables.SHOPPING. 				USERS_ID,data.getUser())
					.set(Tables.SHOPPING.AMOUNT, 		data.getAmount())
					.set(Tables.SHOPPING.SHOPPING_AT, 	new Timestamp(data.getDate().getTime()))
					.set(Tables.SHOPPING.CREATE_AT, 	DSL.currentTimestamp())
					.set(Tables.SHOPPING.UPDATE_AT, 	DSL.currentTimestamp())
					.returning(Tables.SHOPPING.ID)
					.fetchOne();

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
