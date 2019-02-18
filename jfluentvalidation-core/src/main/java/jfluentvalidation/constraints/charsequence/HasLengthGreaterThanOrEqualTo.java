package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the length of the given {@code CharSequence} being validated is greater than or equal to the given size
 */
public class HasLengthGreaterThanOrEqualTo implements Constraint<CharSequence> {

    private final int length;

    public HasLengthGreaterThanOrEqualTo(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.length() >= length;
    }
}
