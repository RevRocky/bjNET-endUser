package bjNET.backRoom;

/**
 * A simple exception class that handles anything going wrong while connecting to the server.
 */
public class UnexpectedArgumentException extends Exception {

    /**
     * Standard Exception Constructor
     * @param message The error message to be printed!
     */
    public UnexpectedArgumentException(String message) {
        super(message);
    }
}
