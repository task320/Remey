package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import settings.SettingReader;
import settings.yaml.object.Settings;

public class DaoSuper {

	Connection conn = null;
	PreparedStatement pStmt = null;
	Settings settings = SettingReader.getSettings();

	public DaoSuper() throws Exception{
		initConection();
	}

	private void initConection() throws Exception{
		try {
			String classDriver = settings.getDbSettings().getDriverClass();
			String jdbcUrl = settings.getDbSettings().getJdbcUrl();
			String dbUser = settings.getDbSettings().getDbUser();
			String dbPass = settings.getDbSettings().getDbPass();

			Class.forName(classDriver);
			conn = DriverManager.getConnection(jdbcUrl,dbUser,dbPass);
		} catch (SQLException | ClassNotFoundException e) {
			throw e;
		}

	}

}
