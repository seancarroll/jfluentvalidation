package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

/**
 * Check that the given {@code CharSequence} being validated contains all the given values
 * <b>in the given order (possibly with other values between them)</b>.
 */
public class ContainsSubsequenceConstraint implements Constraint<CharSequence> {

    private final CharSequence[] sequences;

    public ContainsSubsequenceConstraint(CharSequence... sequences) {
        this.sequences = sequences;
    }

    @Override
    public boolean isValid(CharSequence value) {
        return false;
    }
}
