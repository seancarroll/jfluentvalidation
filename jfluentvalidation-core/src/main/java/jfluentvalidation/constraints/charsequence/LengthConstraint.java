package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class LengthConstraint implements Constraint<CharSequence> {

    private final int min;
    private final int max;

    public LengthConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(CharSequence instance) {

        int length = instance.length();
        if (length < min || (length > max && max != -1)) {
            return false;
        }

        return true;
    }
}
