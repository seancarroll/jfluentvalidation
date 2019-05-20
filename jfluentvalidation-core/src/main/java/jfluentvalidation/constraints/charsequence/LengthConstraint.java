package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

// TODO: do we need this along with all the other Length constraints?

/**
 * Check that the length of the given {@code CharSequence} being validated length is constrained by provided min and max.
 *
 * @param <T>  type of instance to validate.
 */
public class LengthConstraint<T> implements Constraint<T, CharSequence> {

    private final int min;
    private final int max;

    public LengthConstraint(int min, int max) {
        this.min = Ensure.nonnegative(min, "min");
        this.max = max;
        Ensure.argument(max != -1 || max >= min);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        // TODO: I like this because we can use it for exact length, greater than, and less than
        // however doesn't work if we want add inclusive start/end
        int length = validationContext.getPropertyValue().length();
        if (length < min || (length > max && max != -1)) {
            return false;
        }

        return true;
    }
}
