package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Objects;

/**
 * Check that the given {@code Object} being validated is equal to the given object.
 *
 * @param <T>  the target type supported by an implementation.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsEqualsConstraint<T, P> extends AbstractConstraint<T, P> {

    private final P other;

    public IsEqualsConstraint(P other) {
        super(DefaultMessages.IS_EQUALS);
        this.other = other;
    }

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return Objects.equals(context.getPropertyValue(), other);
    }

}
