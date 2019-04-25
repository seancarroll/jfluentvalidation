package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated is not empty.
 */
public class IsNotEmptyConstraint<T, A extends CharSequence> implements Constraint<T, A> {

    @Override
    public boolean isValid(RuleContext<T, A> validationContext) {
        return !validationContext.getPropertyValue().toString().isEmpty();
    }
}
