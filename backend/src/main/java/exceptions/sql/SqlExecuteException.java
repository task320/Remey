package exceptions.sql;

public class SqlExecuteException extends Exception {
    public SqlExecuteException(Throwable exception){
       super(exception);
    }
}
