package exceptions;

public class RepositoryException extends Exception  {

    public RepositoryException(Throwable exception){
        super(exception);
    }

    public RepositoryException(String message){super(message); }
}
