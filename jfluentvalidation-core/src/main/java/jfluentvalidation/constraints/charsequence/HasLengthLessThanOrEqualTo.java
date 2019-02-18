package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the size of the given {@code CharSequence} being validated is less than or equal to the given size
 */
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
