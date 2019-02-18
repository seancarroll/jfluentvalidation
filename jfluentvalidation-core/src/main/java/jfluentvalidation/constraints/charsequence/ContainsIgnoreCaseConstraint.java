package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

/**
 * Checks that the given {@code CharSequence} being validated contains the given sequence, ignoring case.
 *
 */
public class ContainsIgnoreCaseConstraint implements Constraint<CharSequence> {

    private final CharSequence charSequence;

    public ContainsIgnoreCaseConstraint(CharSequence charSequence) {
        this.charSequence = Ensure.notNull(charSequence);
    }

    @Override
    public boolean isValid(CharSequence value) {
        return value.toString().toLowerCase().contains(charSequence.toString().toLowerCase());
    }
}
