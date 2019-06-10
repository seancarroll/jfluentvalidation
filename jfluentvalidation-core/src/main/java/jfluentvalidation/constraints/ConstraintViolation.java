package jfluentvalidation.constraints;

import com.google.common.base.MoreObjects;
import jfluentvalidation.validators.RuleContext;

import java.util.Map;

// TODO: do we like this or ConstraintResult or ValidationFailure or ValidationException
// The name conflicting with javax.validation might not be a good thing.
// I might rename to ConstraintFailure or FailedConstraint
// I forgot why I might want this?
// Is there a ConstraintViolation class defined in javax?
public class ConstraintViolation {

    /**
     * The name of the property.
     */
    private String propertyName;

    /**
     * The error message
     */
    private String errorMessage;

    /**
     * The property value that caused the failure.
     */
    private Object attemptedValue;

    private String errorCode;

    /**
     * These are values for custom formatted message in validator resource files
     * Same formatted message can be reused in UI and with same number of format placeholders
     * Like "Value {0} that you entered should be {1}"
     */
    private Object[] formattedMessageArguments;

    /**
     * formatted message placeholder values
     */
    private Map<String, Object> formattedMessagePlaceholderValues;


    /**
     * Creates a new validation failure.
     * @param propertyName
     * @param errorMessage
     */
    public ConstraintViolation(String propertyName, String errorMessage) {
        this(propertyName, errorMessage, null);
    }

    /**
     * Creates a new validation failure.
     * @param propertyName
     * @param errorMessage
     * @param attemptedValue
     */
    public ConstraintViolation(String propertyName, String errorMessage, Object attemptedValue) {
        this.propertyName = propertyName;
        this.errorMessage = errorMessage;
        this.attemptedValue = attemptedValue;
    }


    public static <T, P> ConstraintViolation create(RuleContext<T, P> context, String errorMessage) {
         return new ConstraintViolation(context.getRule().getPropertyName(), errorMessage, context.getPropertyValue());
    }


//    /// <summary>
//    /// Creates an error validation result for this validator.
//    /// </summary>
//    /// <param name="context">The validator context</param>
//    /// <returns>Returns an error validation result.</returns>
//    protected virtual ValidationFailure CreateValidationError(PropertyValidatorContext context) {
//        var messageBuilderContext = new MessageBuilderContext(context, Options.ErrorMessageSource, this);
//
//        var error = context.Rule.MessageBuilder != null
//            ? context.Rule.MessageBuilder(messageBuilderContext)
//            : messageBuilderContext.GetDefaultMessage();
//
//        var failure = new ValidationFailure(context.PropertyName, error, context.PropertyValue);
//        failure.FormattedMessageArguments = context.MessageFormatter.AdditionalArguments;
//        failure.FormattedMessagePlaceholderValues = context.MessageFormatter.PlaceholderValues;
//        failure.ResourceName = Options.ErrorMessageSource.ResourceName;
//        failure.ErrorCode = (Options.ErrorCodeSource != null)
//            ? Options.ErrorCodeSource.GetString(context)
//            : ValidatorOptions.ErrorCodeResolver(this);
//
//        if (Options.CustomStateProvider != null) {
//            failure.CustomState = Options.CustomStateProvider(context);
//        }
//
//        failure.Severity = Options.Severity;
//        return failure;
//    }

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

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setFormattedMessageArguments(Object[] formattedMessageArguments) {
        this.formattedMessageArguments = formattedMessageArguments;
    }

    public void setFormattedMessagePlaceholderValues(Map<String, Object> formattedMessagePlaceholderValues) {
        this.formattedMessagePlaceholderValues = formattedMessagePlaceholderValues;
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
