package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

public class ContainsOnlyOnceConstraint implements Constraint<CharSequence> {

    private final CharSequence sequence;

    public ContainsOnlyOnceConstraint(CharSequence sequence) {
        this.sequence = sequence;
    }

    // TODO: update so not completely lang-commons
    // TODO: should we just bring in commons-lang????
    @Override
    public boolean isValid(CharSequence instance) {
        int lastIndex = 0;
        int count = 0;

        // TODO: Improve...I'm sure this can be better
        String sequenceAsString = sequence.toString();
        String instanceAsString = instance.toString();
        while (lastIndex != -1) {
            lastIndex = instanceAsString.indexOf(sequenceAsString, lastIndex);

            if (lastIndex != -1) {
                count++;
                if (count >= 2) {
                    return false;
                }
                lastIndex += sequenceAsString.length();
            }
        }

        return true;
    }
}
