package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class HasLengthGreaterThan implements Constraint<CharSequence> {

    private final int length;

    public HasLengthGreaterThan(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return instance.length() > length;
    }
}
