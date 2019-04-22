package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated is {@code Null}, empty or contains only non-whitespace characters.
 */
public class DoesNotContainAnyWhitespacesConstraint<T> implements Constraint<T, CharSequence> {

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return !Strings.isNullOrEmpty(validationContext.getPropertyValue()) && !Strings.containsWhitespaces(validationContext.getPropertyValue());
    }
}
