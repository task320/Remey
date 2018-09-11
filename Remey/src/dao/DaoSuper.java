package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DbInfoPostgresql;

public class DaoSuper {

	Connection conn = null;
	PreparedStatement pStmt = null;

	public DaoSuper() throws Exception{
		initConection(new DbInfoPostgresql());
	}

	private void initConection(DbInfoPostgresql info) throws Exception{
		try {
			Class.forName(info.DRIVER_CLASS);
			conn = DriverManager.getConnection(info.JDBC_URL,info.DB_USER,info.DB_PASS);
		} catch (SQLException | ClassNotFoundException e) {
			throw e;
		}

	}

}
