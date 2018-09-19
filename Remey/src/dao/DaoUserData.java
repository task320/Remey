package dao;

import org.jooq.Record;

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
}
