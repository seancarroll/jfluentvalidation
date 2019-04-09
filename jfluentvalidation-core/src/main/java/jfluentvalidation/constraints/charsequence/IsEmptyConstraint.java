package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated is empty.
 */
public class IsEmptyConstraint<T> implements Constraint<T, CharSequence> {

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return validationContext.getPropertyValue().toString().isEmpty();
    }

}
