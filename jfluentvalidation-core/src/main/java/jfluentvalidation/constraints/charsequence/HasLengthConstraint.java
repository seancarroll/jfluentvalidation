package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class HasLengthConstraint implements Constraint<CharSequence> {

    private final int length;

    public HasLengthConstraint(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.length() == length;
    }
}
