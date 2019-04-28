package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated is empty.
 *
 * @param <T>  type of instance to validate.
 * @param <A>  the type of the actual object being tested by this {@code Constraint}.
 */
public class IsEmptyConstraint<T, A extends CharSequence> implements Constraint<T, A> {

    @Override
    public boolean isValid(RuleContext<T, A> validationContext) {
        return validationContext.getPropertyValue().toString().isEmpty();
    }
}
