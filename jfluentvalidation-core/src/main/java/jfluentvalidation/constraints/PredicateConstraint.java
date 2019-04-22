package jfluentvalidation.constraints;

import jfluentvalidation.validators.RuleContext;

import java.util.function.Predicate;

/**
 *
 * @param <T>
 */
public class PredicateConstraint<T, P> implements Constraint<T, P> {

    private final Predicate<P> predicate;

    public PredicateConstraint(Predicate<P> predicate) {
        this.predicate = predicate;
    }

    @Override
    public boolean isValid(RuleContext<T, P> validationContext) {
        return predicate.test(validationContext.getPropertyValue());
    }
}
