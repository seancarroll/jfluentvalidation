package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;

// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation
public class ContainsConstraint implements Constraint<CharSequence> {

    private final CharSequence[] sequences;

    public ContainsConstraint(CharSequence... sequences) {
        // TODO: ensure checks...maybe notNull/not empty/null elements
        this.sequences = sequences;
    }

    @Override
    public boolean isValid(CharSequence instance) {

        // TODO: Its probably best that we capture all of the sequence that are not in the string
        // How to do that? some sort of context? Map<Object, Object> which can be used within the localization string?
        String instanceAsString = instance.toString();
        for (CharSequence sequence : sequences) {
            if (!instanceAsString.contains(sequence)) {
                return false;
            }
        }
        return true;
    }
}
