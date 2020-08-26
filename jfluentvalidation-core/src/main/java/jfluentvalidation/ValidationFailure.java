package jfluentvalidation;

import com.google.common.base.MoreObjects;

// TODO: add property path field?

/**
 *
 */
public class ValidationFailure {

    /**
     * The name of the property.
     */
    private String propertyName;

    /**
     * The error message
     */
    private String errorMessage;

    private String errorCode;

    /**
     * The property value that caused the failure.
     */
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

    /**
     *
     * @return
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     *
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     *
     * @return
     */
    public Object getAttemptedValue() {
        return attemptedValue;
    }

    @Override
    public String toString() {
        // TODO: swap out guava toString builder with something else
        return MoreObjects.toStringHelper(this)
            .add("propertyName", getPropertyName())
            .add("errorCode", getErrorCode())
            .add("errorMessage", getErrorMessage())
            .add("attemptedValue", getAttemptedValue())
            .toString();
    }
}
