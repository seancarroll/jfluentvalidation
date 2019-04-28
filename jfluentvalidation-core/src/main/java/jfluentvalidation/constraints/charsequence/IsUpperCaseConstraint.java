package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated contains only uppercase characters.
 * @param <T> type of instance to validate
 */
public class IsUpperCaseConstraint<T> implements Constraint<T, CharSequence> {

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        // TODO: should this use locale?
        return validationContext.getPropertyValue().equals(validationContext.getPropertyValue().toString().toUpperCase());
    }
}
