package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is less than the given size
 * @param <T> type of instance to validate
 */
public class HasLengthLessThanConstraint<T> implements Constraint<T, CharSequence> {

    private final int length;

    public HasLengthLessThanConstraint(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().length() < length;
    }
}
