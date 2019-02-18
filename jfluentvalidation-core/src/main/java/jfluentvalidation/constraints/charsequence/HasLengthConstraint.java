package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

/**
 * Check that the size of the given {@code CharSequence} being validated is equal to the given length.
 */
public class HasLengthConstraint implements Constraint<CharSequence> {

    private final int length;

    public HasLengthConstraint(int length) {
        this.length = Ensure.nonnegative(length, "length");
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.length() == length;
    }
}
