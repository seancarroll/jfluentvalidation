package jfluentvalidation.validators;

import java.util.HashMap;
import java.util.Map;


// TODO: what package should this live in?
// Name from fluentValidation. Can we think of a better name?
// Should this be an interface?
// fluentValidation has a PropertyValidatorContext which contains
// private readonly Lazy<object> _propertyValueContainer;
// public string PropertyName { get; private set; }
//   _propertyValueContainer = new Lazy<object>( () => {
//    var value = rule.PropertyFunc(parentContext.InstanceToValidate);
//    if (rule.Transformer != null) value = rule.Transformer(value);
//    return value;
//    });
/**
 * Validation context
 * @param <T> type of instance to validate
 */
public class ValidationContext<T, P> {

    // TODO: could this just store the subject which has the instanceToValidate, propertyFunc, as well as the propertyValue?

    /**
     * The object currently being validated
     */
    private final T instanceToValidate;

    /**
     * The value of the property being validated.
     */
    private P propertyValue;

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

    public T getInstanceToValidate() {
        return instanceToValidate;
    }

    public P getPropertyValue() {
        return propertyValue;
    }

    public Map<String, Object> getContextData() {
        return contextData;
    }
}
