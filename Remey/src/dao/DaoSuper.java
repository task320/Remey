package dao;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import constant.Env;
import settings.SettingReader;
import settings.yaml.object.Settings;

public class DaoSuper {

	Connection conn = null;
	PreparedStatement pStmt = null;
	Settings settings = SettingReader.getSettings();
	DSLContext create = null;

	public DaoSuper() throws Exception{
		initConection();
	}

	private void initConection() throws Exception{
		try {
			String env_mode = System.getenv(Env.APP_ENV_MODE);

			String classDriver = settings.getDbSettings().getDriverClass();
			String jdbcUrl = "";
			String dbUser = "";
			String dbPass = "";

			//環境変数により、接続先を変更
			if(env_mode.equals(Env.ENV_DEVELOPMENT)){
				jdbcUrl = settings.getDbSettings().getJdbcUrl();
				dbUser = settings.getDbSettings().getDbUser();
				dbPass = settings.getDbSettings().getDbPass();
			}else if(env_mode.equals(Env.ENV_PRODUCTION)){
				 URI dbUri = new URI(System.getenv("DATABASE_URL"));
				 jdbcUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
				 dbUser = dbUri.getUserInfo().split(":")[0];
				 dbPass = dbUri.getUserInfo().split(":")[1];

			}else{
				throw new Exception("The environment variable setting is not done");
			}

			Class.forName(classDriver);
			conn = DriverManager.getConnection(jdbcUrl,dbUser,dbPass);
			create = DSL.using(conn, SQLDialect.POSTGRES);
		} catch (SQLException | ClassNotFoundException e) {
			throw e;
		}

	}

	protected void closeConn() throws Exception{
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
