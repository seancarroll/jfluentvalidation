package jfluentvalidation;

// TODO: Move into core package
/**
 *
 */
public class IORuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Creates a new <code>{@link IORuntimeException}</code>.
     * @param message the detail message.
     */
    public IORuntimeException(String message) {
        super(message);
    }

    /**
     * Creates a new <code>{@link IORuntimeException}</code>.
     * @param message the detail message.
     * @param cause the cause of the error.
     */
    public IORuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
