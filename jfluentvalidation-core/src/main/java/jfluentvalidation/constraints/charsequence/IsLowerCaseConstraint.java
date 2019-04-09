package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated contains only lowercase characters.
 */
public class IsLowerCaseConstraint<T> implements Constraint<T, CharSequence> {

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        // TODO: should this use locale?
        return validationContext.getPropertyValue().equals(validationContext.getPropertyValue().toString().toLowerCase());
    }
}
