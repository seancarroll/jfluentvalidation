package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the length of the given {@code CharSequence} being validated is less than or equal to the given size
 */
public class HasLengthLessThanOrEqualToConstraint implements Constraint<CharSequence> {

    private final int length;

    public HasLengthLessThanOrEqualToConstraint(int length) {
        this.length = length;
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.length() <= length;
    }
}
