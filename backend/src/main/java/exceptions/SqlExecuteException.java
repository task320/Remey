package exceptions;

public class SqlExecuteException extends Exception {

    public SqlExecuteException(Throwable exception){
       super(exception);
    }

    public SqlExecuteException(String message){super(message);}
}
