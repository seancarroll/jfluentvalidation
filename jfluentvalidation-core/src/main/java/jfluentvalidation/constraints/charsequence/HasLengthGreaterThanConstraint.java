package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is greater than the given length.
 *
 * @param <T>  type of instance to validate.
 */
public class HasLengthGreaterThanConstraint<T> implements Constraint<T, CharSequence> {

    private final int length;

    public HasLengthGreaterThanConstraint(int length) {
        this.length = Ensure.nonnegative(length, "length");
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().length() > length;
    }
}
