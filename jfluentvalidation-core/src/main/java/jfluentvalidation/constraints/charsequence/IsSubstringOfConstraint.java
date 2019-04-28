package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T> type of instance to validate
 */
public class IsSubstringOfConstraint<T> implements Constraint<T, CharSequence> {

    private final CharSequence sequence;

    public IsSubstringOfConstraint(CharSequence sequence) {
        this.sequence = Ensure.notNull(sequence);
    }

    // TODO: comparison strategy
    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().toString().contains(sequence);
    }
}
