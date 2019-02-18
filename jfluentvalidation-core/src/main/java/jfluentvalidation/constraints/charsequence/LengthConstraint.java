package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

// TODO: do we need this along with all the other Length constraints?
public class LengthConstraint implements Constraint<CharSequence> {

    private final int min;
    private final int max;

    public LengthConstraint(int min, int max) {
        this.min = Ensure.nonnegative(min, "min");
        this.max = max;
        Ensure.argument(max != -1 || max >= min);
    }

    @Override
    public boolean isValid(CharSequence value) {

        int length = value.length();
        if (length < min || (length > max && max != -1)) {
            return false;
        }

        return true;
    }
}
