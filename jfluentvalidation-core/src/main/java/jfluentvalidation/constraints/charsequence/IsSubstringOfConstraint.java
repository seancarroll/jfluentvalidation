package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class IsSubstringOfConstraint implements Constraint<CharSequence> {

    private final CharSequence sequence;

    public IsSubstringOfConstraint(CharSequence sequence) {
        this.sequence = sequence;
    }

    // TODO: comparison strategy
    @Override
    public boolean isValid(CharSequence instance) {

//        assertNotNull(info, actual);
//        checkNotNull(sequence, "Expecting CharSequence not to be null");
//        if (stringContains(sequence.toString(), actual.toString())) return;

        instance.toString().contains(sequence);

        return false;
    }
}
