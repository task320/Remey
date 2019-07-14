package infrastructure.settings.yaml.object;
import infrastructure.settings.yaml.object.auth.AuthSettings;
import infrastructure.settings.yaml.object.db.DbSettings;

public class Settings {
	private DbSettings dbSettings;
	private AuthSettings authSettings;

	public void setDbSettings(DbSettings dbSettings) {
		this.dbSettings = dbSettings;
	}
	public void setAuthSettings(AuthSettings authSettings) {
		this.authSettings = authSettings;
	}

	public DbSettings getDbSettings(){
		return dbSettings;
	}

	public AuthSettings getAuthSettings(){
		return authSettings;
	}



}
