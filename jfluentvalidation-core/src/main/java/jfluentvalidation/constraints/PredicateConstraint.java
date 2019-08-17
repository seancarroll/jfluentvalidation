package jfluentvalidation.constraints;

import jfluentvalidation.validators.RuleContext;

import java.util.function.Predicate;

/**
 *
 * @param <T>  the target type supported by an implementation.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class PredicateConstraint<T, P> extends AbstractConstraint<T, P> {

    // The specified condition was not met for '{PropertyName}'.
    private static final String DEFAULT_MESSAGE = "{jfluentvalidation.constraints.Predicate.message}";

    private final Predicate<P> predicate;

    // TODO: allow error message as override?
    public PredicateConstraint(Predicate<P> predicate) {
        super(DEFAULT_MESSAGE);
        this.predicate = predicate;
    }

    @Override
    public boolean isValid(RuleContext<T, P> validationContext) {
        return predicate.test(validationContext.getPropertyValue());
    }

//    @Override
//    public String getMessage() {
//        return DEFAULT_MESSAGE;
//    }
}
