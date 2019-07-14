package infrastructure.connection;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import constant.Env;
import infrastructure.settings.yaml.object.Settings;

public class JooqPostgresqlConnection implements IDBConnection {

	Connection conn = null;
	DSLContext create = null;

	public JooqPostgresqlConnection(Settings settings) throws Exception{
		initConnection(settings);
	}

	private void initConnection(Settings settings) throws Exception{
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

			org.jooq.conf.Settings jooqSettings = new org.jooq.conf.Settings();
			jooqSettings.withExecuteLogging(true);

			create = DSL.using(conn, SQLDialect.POSTGRES, jooqSettings);
		} catch (SQLException | ClassNotFoundException e) {
			throw e;
		}
	}

	public void closeConnection() throws Exception{
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
