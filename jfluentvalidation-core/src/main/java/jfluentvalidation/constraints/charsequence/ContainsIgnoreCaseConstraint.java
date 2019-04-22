package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks that the given {@code CharSequence} being validated contains the given sequence, ignoring case.
 *
 */
public class ContainsIgnoreCaseConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence charSequence;

    public ContainsIgnoreCaseConstraint(CharSequence charSequence) {
        this.charSequence = Ensure.notNull(charSequence);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().toString().toLowerCase().contains(charSequence.toString().toLowerCase());
    }
}
