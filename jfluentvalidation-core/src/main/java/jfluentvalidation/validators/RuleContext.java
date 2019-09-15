package jfluentvalidation.validators;

import jfluentvalidation.internal.MessageFormatter;
import jfluentvalidation.rules.Rule;

// QUESTION: would ConstraintContext be a better name?
/**
 *
 * @param <T>
 * @param <P>
 */
public class RuleContext<T, P> {

    private final ValidationContext<T> validationContext;
    private final Rule<T, P> rule;
    private final P propertyValue;
    private final MessageFormatter messageFormatter;

    // TODO: I dont know where I want this to belong but for right now I'm putting it here
    // TODO: should this be a linkedhashmap?
    //private final Map<String, Object> additionalArguments = new HashMap<>();

    //

    /**
     *
     * @param validationContext
     * @param rule
     */
    public RuleContext(ValidationContext<T> validationContext, Rule<T, P> rule) {
        this.validationContext = validationContext;
        this.rule = rule;
        this.propertyValue = rule.getPropertyFunc().apply(validationContext.getInstanceToValidate());
        this.messageFormatter = new MessageFormatter();
    }

    /**
     *
     * @param validationContext
     * @param rule
     * @param propertyValue
     */
    public RuleContext(ValidationContext<T> validationContext, Rule<T, P> rule, P propertyValue) {
        this.validationContext = validationContext;
        this.rule = rule;
        this.propertyValue = propertyValue;
        this.messageFormatter = new MessageFormatter();
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

    public MessageFormatter getMessageFormatter() {
        return messageFormatter;
    }

//    public void appendArgument(String name, Object arg) {
//        this.additionalArguments.put(name, arg);
//    }
//
//    public Map<String, Object> getAdditionalArguments() {
//        return additionalArguments;
//    }
}
