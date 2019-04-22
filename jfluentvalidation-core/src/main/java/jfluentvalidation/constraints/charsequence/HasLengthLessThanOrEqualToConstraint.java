package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is less than or equal to the given size
 */
public class HasLengthLessThanOrEqualToConstraint<T> implements Constraint<T, CharSequence> {

    private final int length;

    public HasLengthLessThanOrEqualToConstraint(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().length() <= length;
    }
}
