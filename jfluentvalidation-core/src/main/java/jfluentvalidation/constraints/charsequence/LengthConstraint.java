package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

public class LengthConstraint implements Constraint<CharSequence> {

    private final int min;
    private final int max;

    public LengthConstraint(int min, int max) {
        this.min = Ensure.nonnegative(min, "min");
        this.max = Ensure.nonnegative(max, "max");
        Ensure.argument(max < min);
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
