package settings.yaml.object;
import settings.yaml.object.db.DbSettings;

public class Settings {
	private DbSettings dbSettings;

	public void setDbSettings(DbSettings dbSettings) {
		this.dbSettings = dbSettings;
	}

	public DbSettings getDbSettings(){
		return dbSettings;
	}



}
