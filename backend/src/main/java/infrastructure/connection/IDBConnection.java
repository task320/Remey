package infrastructure.connection;

import infrastructure.settings.yaml.object.Settings;
import org.jooq.DSLContext;

import java.sql.Connection;

public interface IDBConnection {
    Connection conn = null;
    DSLContext create = null;
    void closeConnection() throws Exception;
}
