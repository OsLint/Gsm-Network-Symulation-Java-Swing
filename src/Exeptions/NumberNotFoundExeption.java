package Exeptions;

public class NumberNotFoundExeption extends Exception{
    public NumberNotFoundExeption () {
        super();
    }
    public NumberNotFoundExeption (String message) {
        super(message);
    }
    public NumberNotFoundExeption (String message, Throwable cause) {
        super(message,cause);
    }
}
