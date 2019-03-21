package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

/**
 * Check that the length of the given {@code CharSequence} being validated is greater than the given size
 */
public class HasLengthGreaterThanConstraint implements Constraint<CharSequence> {

    private final int length;

    public HasLengthGreaterThanConstraint(int length) {
        this.length = Ensure.nonnegative(length, "length");
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.length() > length;
    }
}