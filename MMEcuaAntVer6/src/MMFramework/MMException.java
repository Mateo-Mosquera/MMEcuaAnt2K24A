package MMFramework;

public class MMException extends Exception {
    public MMException(String message) {
        super(message);
    }

    public MMException(String message, Throwable cause) {
        super(message, cause);
    }
}
