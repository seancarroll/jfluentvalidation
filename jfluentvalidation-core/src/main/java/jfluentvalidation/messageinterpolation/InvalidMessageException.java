package jfluentvalidation.messageinterpolation;

// TODO: terrible name
public class InvalidMessageException extends RuntimeException {

    // Replaces following Hibernate Validator Exceptions
//    @Message(id = 168,
//        value = "The message descriptor '%1$s' contains an unbalanced meta character '%2$c'.")
//    MessageDescriptorFormatException getUnbalancedBeginEndParameterException(String messageDescriptor, char character);

//    @Message(id = 169,
//        value = "The message descriptor '%1$s' has nested parameters.")
//    MessageDescriptorFormatException getNestedParameterException(String messageDescriptor);

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
