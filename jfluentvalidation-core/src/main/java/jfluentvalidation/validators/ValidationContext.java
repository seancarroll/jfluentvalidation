package jfluentvalidation.validators;

import java.util.HashMap;
import java.util.Map;


// TODO: what package should this live in?
// Name from fluentValidation. Can we think of a better name?
// Should this be an interface?
/**
 * Validation context
 * @param <T> type of instance to validate
 */
public class ValidationContext<T> {

    /**
     * Object to validate
     */
    private final T instanceToValidate;

    /**
     * Additional data associated with the validation request
     */
    private final Map<String, Object> contextData = new HashMap<>();

    /**
     * Creates a new validation context
     * @param instanceToValidate
     */
    public ValidationContext(T instanceToValidate) {
        this.instanceToValidate = instanceToValidate;
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }
}
