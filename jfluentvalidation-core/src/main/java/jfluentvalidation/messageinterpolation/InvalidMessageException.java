package jfluentvalidation.messageinterpolation;

// TODO: terrible name
public class InvalidMessageException extends RuntimeException {

    private String message;
    private char character;

    public InvalidMessageException(String message, char character) {
        this.message = message;
        this.character = character;
    }

    public InvalidMessageException(String message) {
        this.message = message;
    }

}
