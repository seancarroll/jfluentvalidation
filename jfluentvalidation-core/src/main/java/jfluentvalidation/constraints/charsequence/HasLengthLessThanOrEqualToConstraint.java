package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the length of the given {@code CharSequence} being validated is less than or equal to the given length
 *
 * @param <T>  type of instance to validate.
 */
public class HasLengthLessThanOrEqualToConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final int length;

    public HasLengthLessThanOrEqualToConstraint(int length) {
        super(DefaultMessages.CHARSEQUENCE_HAS_LENGTH_LESS_THAN_OR_EQUAL);
        this.length = length;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().length() <= length;
    }
}
