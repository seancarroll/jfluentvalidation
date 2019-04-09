package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

// TODO: do we need this along with all the other Length constraints?
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

        int length = validationContext.getPropertyValue().length();
        if (length < min || (length > max && max != -1)) {
            return false;
        }

        return true;
    }
}
