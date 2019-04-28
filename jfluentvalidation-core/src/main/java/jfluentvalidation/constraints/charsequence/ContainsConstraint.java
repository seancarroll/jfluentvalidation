package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation

/**
 * Check that the given {@code CharSequence} being validated contains the given CharSequences.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence[] sequences;

    public ContainsConstraint(CharSequence... sequences) {
        this.sequences = Ensure.validCharSequenceArray(sequences);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {

        // TODO: Its probably best that we capture all of the sequence that are not in the string
        // How to do that? some sort of context? Map<Object, Object> which can be used within the localization string?
        String instanceAsString = validationContext.getPropertyValue().toString();
        for (CharSequence sequence : sequences) {
            if (!instanceAsString.contains(sequence)) {
                return false;
            }
        }
        return true;
    }
}
