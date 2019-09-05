package infrastructure.connection;

import infrastructure.settings.yaml.object.Settings;
import org.jooq.DSLContext;

import java.sql.Connection;

public abstract class DBConnection {
    protected Connection conn = null;
    public DSLContext create = null;

    public DBConnection(Settings settings) throws Exception{
        initConnection(settings);
    }

    protected abstract void initConnection(Settings settings) throws Exception;
}
