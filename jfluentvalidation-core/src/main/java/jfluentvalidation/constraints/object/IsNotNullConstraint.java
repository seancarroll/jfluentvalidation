package jfluentvalidation.constraints.object;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 * Check that the given {@code Object} being validated is not {@code null}.
 *
 * @param <T>  the target type supported by an implementation
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotNullConstraint<T, P> extends AbstractConstraint<T, P> {

    public IsNotNullConstraint() {
        super(DefaultMessages.IS_NOT_NULL);
    }

    @Override
    public boolean isValid(ConstraintContext<T, P> context) {
        return context.getPropertyValue() != null;
    }

}
