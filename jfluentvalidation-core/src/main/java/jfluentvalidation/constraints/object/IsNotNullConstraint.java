package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code Object} being validated is not {@code null}.
 *
 * @param <T>  the target type supported by an implementation
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotNullConstraint<T, P> implements Constraint<T, P> {

    @Override
    public boolean isValid(RuleContext<T, P> context) {
        return context.getPropertyValue() != null;
    }
}
