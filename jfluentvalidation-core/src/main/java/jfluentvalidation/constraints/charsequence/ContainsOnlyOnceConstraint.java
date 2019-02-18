package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

// TODO: allow custom comparison strategy

/**
 * Check that the given {@code CharSequence} being validated contains the given sequence only once.
 *
 */
public class ContainsOnlyOnceConstraint implements Constraint<CharSequence> {

    private final CharSequence sequence;

    public ContainsOnlyOnceConstraint(CharSequence sequence) {
        this.sequence = Ensure.notNull(sequence);
    }

    @Override
    public boolean isValid(CharSequence value) {
        if (Strings.isNull(value)) {
            return false;
        }

        String sequenceAsString = sequence.toString();
        String instanceAsString = value.toString();

        // TODO: do we want to count all instances and include in message/context
        int count = 0;
        int fromIndex = 0;
        while ((fromIndex = instanceAsString.indexOf(sequenceAsString, fromIndex)) != -1 ) {
            count++;
            if (count >= 2) {
                return false;
            }
            fromIndex += sequenceAsString.length();
        }

        return count == 1;
    }
}
