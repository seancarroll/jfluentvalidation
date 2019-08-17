package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

// TODO: do we need this along with all the other Length constraints?

/**
 * Check that the length of the given {@code CharSequence} being validated length is constrained by provided min and max.
 *
 * @param <T>  type of instance to validate.
 */
public class LengthConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    private final int min;
    private final int max;

    public LengthConstraint(int min, int max) {
        super(DefaultMessages.CHARSEQUENCE_LENGTH);
        this.min = Ensure.nonnegative(min, "min");
        this.max = max;
        Ensure.argument(max != -1 || max >= min);
    }

    @Override
    public boolean isValid(RuleContext<T, A> validationContext) {
        // TODO: I like this because we can use it for exact length, greater than, and less than
        // however doesn't work if we want add inclusive start/end
        int length = validationContext.getPropertyValue().length();
        if (length < min || (length > max && max != -1)) {
            return false;
        }

        return true;
    }
}
