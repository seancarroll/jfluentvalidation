package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class HasLengthLessThanConstraint implements Constraint<CharSequence> {

    private final int length;

    public HasLengthLessThanConstraint(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return instance.length() < length;
    }
}
