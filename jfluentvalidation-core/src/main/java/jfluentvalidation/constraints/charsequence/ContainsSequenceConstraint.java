package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

// TODO: given we have multiple of these based on type need to either change the name to include the type we are comparing
// or I guess we could make this take an object and have logic to determine type and perform the appropriate validation

/**
 * Check that the given {@code CharSequence} being validated contains the given sequence of charSequence
 * without any other charSequences between them.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsSequenceConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final CharSequence[] sequences;

    public ContainsSequenceConstraint(CharSequence... sequences) {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_SEQUENCE);
        this.sequences = sequences;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        // TODO: implement
        return false;
    }
}
