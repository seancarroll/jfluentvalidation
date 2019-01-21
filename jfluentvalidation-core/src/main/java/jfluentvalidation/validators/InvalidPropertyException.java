package jfluentvalidation.validators;

// TODO: not sure about this
public class InvalidPropertyException extends RuntimeException {

    public InvalidPropertyException(String message) {
        super(message);
    }

    public InvalidPropertyException(String message, Exception e) {
        super(message, e);
    }

}
