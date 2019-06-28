package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is greater than the given length.
 *
 * @param <T>  type of instance to validate.
 */
public class HasLengthGreaterThanConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final int length;

    public HasLengthGreaterThanConstraint(int length) {
        super(DefaultMessages.CHARSEQUENCE_HAS_LENGTH_GREATER_THAN);
        this.length = Ensure.nonnegative(length, "length");
    }

    @Override
    public boolean isValid(RuleContext<T, A> validationContext) {
        return validationContext.getPropertyValue().length() > length;
    }
}
