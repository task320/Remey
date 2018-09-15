package settings.yaml.object.db;

public class DbSettings {
	private String driverClass;
	private String jdbcUrl;
	private String dbUser;
	private String dbPass;

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	public void setDbPass(String dbPass) {
		this.dbPass = dbPass;
	}
	public String getDriverClass() {
		return driverClass;
	}
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public String getDbUser() {
		return dbUser;
	}
	public String getDbPass() {
		return dbPass;
	}

}
