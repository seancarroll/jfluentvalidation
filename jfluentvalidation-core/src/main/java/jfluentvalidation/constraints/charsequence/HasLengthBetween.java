package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class HasLengthBetween implements Constraint<CharSequence> {

    private final int min;
    private final int max;

    public HasLengthBetween(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        int len = instance.length();
        return len >= min && len <= max;
    }
}
