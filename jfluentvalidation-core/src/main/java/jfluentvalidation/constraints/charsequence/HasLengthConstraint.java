package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is equal to the given length.
 *
 * @param <T>  type of instance to validate.
 */
public class HasLengthConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final int length;

    public HasLengthConstraint(int length) {
        super(DefaultMessages.CHARSEQUENCE_HAS_LENGTH);
        this.length = Ensure.nonnegative(length, "length");
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().length() == length;
    }
}
