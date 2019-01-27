package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class ContainsSubsequenceConstraint implements Constraint<CharSequence> {

    private final CharSequence[] sequences;

    public ContainsSubsequenceConstraint(CharSequence... sequences) {
        this.sequences = sequences;
    }

    @Override
    public boolean isValid(CharSequence instance) {
        return false;
    }
}
