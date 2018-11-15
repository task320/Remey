package dao;

import org.jooq.Record;
import org.jooq.impl.DSL;

import remey.jooq.Tables;

public class DaoUserData extends DaoSuper {

	public DaoUserData() throws Exception {
		super();
	}

	public Record getUserData(String user) throws Exception{
		try{
			 Record result = create
					.select()
					.from(Tables.USERS)
					.where(Tables.USERS.ID.eq(user))
					.fetchOne();

			return result;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			closeConn();
		}
	}

	public int selectUserCountByGoogleInfo(String sub) throws Exception{
		try{
			 int count = create
					.selectCount()
					.from(Tables.USERS)
					.where(Tables.USERS.ID.eq(sub)).groupBy(Tables.USERS.ID)
					.fetchOne(0, int.class);
			return count;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			closeConn();
		}
	}

	public int insertUserData(String userId, String password) throws Exception{
		try{
			 int insertCount = create
					.insertInto(Tables.USERS)
					.set(Tables.USERS.ID, userId)
					.set(Tables.USERS.PASSWORD, password)
					.set(Tables.USERS.CREATE_AT, DSL.currentTimestamp())
					.set(Tables.USERS.UPDATE_AT, DSL.currentTimestamp())
					.execute();

			return insertCount;
		}
		catch(Exception e){
			throw e;
		}
		finally{
			closeConn();
		}
	}
}
