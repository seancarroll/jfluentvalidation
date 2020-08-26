package jfluentvalidation.validators;

import jfluentvalidation.messageinterpolation.MessageContext;
import jfluentvalidation.rules.Rule;

/**
 *
 * @param <T>
 * @param <P>
 */
public class ConstraintContext<T, P> {

    private final ValidationContext<T> validationContext;
    private final Rule<T, P> rule;
    private final P propertyValue;
    private final MessageContext messageContext;

    /**
     *
     * @param validationContext
     * @param rule
     */
    public ConstraintContext(ValidationContext<T> validationContext, Rule<T, P> rule) {
        this.validationContext = validationContext;
        this.rule = rule;
        this.propertyValue = rule.getPropertyFunc().apply(validationContext.getInstanceToValidate());
        this.messageContext = new MessageContext();
    }

    /**
     *
     * @param validationContext
     * @param rule
     * @param propertyValue
     */
    public ConstraintContext(ValidationContext<T> validationContext, Rule<T, P> rule, P propertyValue) {
        this.validationContext = validationContext;
        this.rule = rule;
        this.propertyValue = propertyValue;
        this.messageContext = new MessageContext();
    }

    public T getInstanceToValidate() {
        return validationContext.getInstanceToValidate();
    }

    public P getPropertyValue() {
        return propertyValue;
    }

    public ValidationContext<T> getValidationContext() {
        return validationContext;
    }

    public Rule<T, P> getRule() {
        return rule;
    }

    public MessageContext getMessageContext() {
        return messageContext;
    }

}
