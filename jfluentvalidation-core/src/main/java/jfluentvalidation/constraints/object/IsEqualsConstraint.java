package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Objects;

/**
 * Check that the given {@code Object} being validated is equal to the given object.
 * @param <T> the target type supported by an implementation
 * @param <P>
 */
public class IsEqualsConstraint<T, P> implements Constraint<T, P> {

    private final P other;

    public IsEqualsConstraint(P other) {
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return Objects.equals(context.getPropertyValue(), other);
    }

}
