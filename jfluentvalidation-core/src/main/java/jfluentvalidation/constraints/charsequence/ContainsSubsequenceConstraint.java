package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated contains all the given values
 * <b>in the given order (possibly with other values between them)</b>.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsSubsequenceConstraint<T> extends AbstractConstraint<T, CharSequence> {

    private final CharSequence[] sequences;

    public ContainsSubsequenceConstraint(CharSequence... sequences) {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_SUBSEQUENCE);
        this.sequences = sequences;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return false;
    }
}
