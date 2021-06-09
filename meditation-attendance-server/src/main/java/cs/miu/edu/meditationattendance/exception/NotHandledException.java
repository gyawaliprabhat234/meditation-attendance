package cs.miu.edu.meditationattendance.exception;

public class NotHandledException extends Exception{
    public NotHandledException(){
        super();
    }
    public NotHandledException(String message) {
        super(message);
    }
    public NotHandledException(String message, Throwable cause) {
        super(message, cause);
    }
}
