package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the length of the given {@code CharSequence} being validated is less than the given size
 */
public class HasLengthLessThanConstraint implements Constraint<CharSequence> {

    private final int length;

    public HasLengthLessThanConstraint(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.length() < length;
    }
}
