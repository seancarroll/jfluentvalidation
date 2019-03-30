package jfluentvalidation;

/**
 *
 */
public class ValidationFailure {

    private String propertyName;
    private String errorMessage;
    private String errorCode;
    private Object attemptedValue;

    /**
     *
     * @param propertyName
     * @param attemptedValue
     */
    public ValidationFailure(String propertyName, Object attemptedValue) {
        this(propertyName, null, attemptedValue);
    }

    /**
     *
     * @param propertyName
     * @param errorMessage
     * @param attemptedValue
     */
    public ValidationFailure(String propertyName, String errorMessage, Object attemptedValue) {
        this.propertyName = propertyName;
        this.errorMessage = errorMessage;
        this.attemptedValue = attemptedValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object getAttemptedValue() {
        return attemptedValue;
    }

    @Override
    public String toString() {
        return "";
    }
}
