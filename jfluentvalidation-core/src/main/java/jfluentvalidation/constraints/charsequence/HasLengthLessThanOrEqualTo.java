package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class HasLengthLessThanOrEqualTo implements Constraint<CharSequence> {

    private final int length;

    public HasLengthLessThanOrEqualTo(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.length() <= length;
    }
}
