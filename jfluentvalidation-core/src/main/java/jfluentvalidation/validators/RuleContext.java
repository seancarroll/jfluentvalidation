package jfluentvalidation.validators;

import jfluentvalidation.rules.Rule;

// QUESTION: would ConstraintContext be a better name?
/**
 *
 * @param <T>
 * @param <P>
 */
public class RuleContext<T, P> {

    private final ValidationContext<T, P> validationContext;
    private final Rule<T, P> rule;
    private final P propertyValue;

    /**
     *
     * @param validationContext
     * @param rule
     */
    public RuleContext(ValidationContext<T, P> validationContext, Rule<T, P> rule) {
        this.validationContext = validationContext;
        this.rule = rule;
        this.propertyValue = rule.getPropertyFunc().apply(validationContext.getInstanceToValidate());
    }

    /**
     *
     * @param validationContext
     * @param rule
     * @param propertyValue
     */
    public RuleContext(ValidationContext<T, P> validationContext, Rule<T, P> rule, P propertyValue) {
        this.validationContext = validationContext;
        this.rule = rule;
        this.propertyValue = propertyValue;
    }

    public T getInstanceToValidate() {
        return validationContext.getInstanceToValidate();
    }

    public P getPropertyValue() {
        return propertyValue;
    }

    public ValidationContext<T, P> getValidationContext() {
        return validationContext;
    }

    public Rule<T, P> getRule() {
        return rule;
    }

}
