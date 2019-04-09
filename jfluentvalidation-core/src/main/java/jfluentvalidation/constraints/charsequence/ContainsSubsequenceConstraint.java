package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated contains all the given values
 * <b>in the given order (possibly with other values between them)</b>.
 */
public class ContainsSubsequenceConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence[] sequences;

    public ContainsSubsequenceConstraint(CharSequence... sequences) {
        this.sequences = sequences;
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return false;
    }
}
