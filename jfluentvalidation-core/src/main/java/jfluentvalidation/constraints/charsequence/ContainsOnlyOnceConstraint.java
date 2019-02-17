package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

// TODO: allow custom comparison strategy
public class ContainsOnlyOnceConstraint implements Constraint<CharSequence> {

    private final CharSequence sequence;

    public ContainsOnlyOnceConstraint(CharSequence sequence) {
        this.sequence = Ensure.notNull(sequence);
    }

    // TODO: update so not completely lang-commons
    // TODO: should we just bring in commons-lang????
    @Override
    public boolean isValid(CharSequence instance) {
        if (Strings.isNull(instance)) {
            return false;
        }

        // TODO: do we want to count all instances?
        int count = 0;
        int fromIndex = 0;
        String sequenceAsString = sequence.toString();
        String instanceAsString = instance.toString();
        while ((fromIndex = instanceAsString.indexOf(sequenceAsString, fromIndex)) != -1 ) {
            count++;
            if (count >= 2) {
                return false;
            }
            fromIndex += sequenceAsString.length();
        }



//        int lastIndex = 0;
//        int count = 0;
//
//        // TODO: Improve...I'm sure this can be better
//        String sequenceAsString = sequence.toString();
//        String instanceAsString = instance.toString();
//        while (lastIndex != -1) {
//            lastIndex = instanceAsString.indexOf(sequenceAsString, lastIndex);
//            if (lastIndex != -1) {
//                count++;
//                if (count >= 2) {
//                    return false;
//                }
//                lastIndex += sequenceAsString.length();
//            }
//        }

        return count == 1;
    }
}
