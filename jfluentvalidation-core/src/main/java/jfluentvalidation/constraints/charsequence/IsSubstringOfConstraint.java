package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

public class IsSubstringOfConstraint implements Constraint<CharSequence> {

    private final CharSequence sequence;

    public IsSubstringOfConstraint(CharSequence sequence) {
        this.sequence = Ensure.notNull(sequence);
    }

    // TODO: comparison strategy
    @Override
    public boolean isValid(CharSequence value) {

//        assertNotNull(info, actual);
//        checkNotNull(sequence, "Expecting CharSequence not to be null");
//        if (stringContains(sequence.toString(), actual.toString())) return;

        value.toString().contains(sequence);

        return false;
    }
}
