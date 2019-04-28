package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is greater than or equal to the given size
 * @param <T> type of instance to validate
 */
public class HasLengthGreaterThanOrEqualToConstraint<T> implements Constraint<T, CharSequence> {

    private final int length;

    public HasLengthGreaterThanOrEqualToConstraint(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().length() >= length;
    }
}
