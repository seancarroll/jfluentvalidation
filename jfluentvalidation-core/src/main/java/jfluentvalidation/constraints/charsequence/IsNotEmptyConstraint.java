package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated is not empty.
 *
 * @param <T>  type of instance to validate.
 * @param <A>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsNotEmptyConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    public IsNotEmptyConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return !context.getPropertyValue().toString().isEmpty();
    }
}
