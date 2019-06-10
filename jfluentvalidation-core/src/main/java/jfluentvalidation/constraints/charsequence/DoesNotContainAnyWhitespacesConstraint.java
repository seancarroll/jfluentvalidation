package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated is {@code Null}, empty or contains only non-whitespace characters.
 *
 * @param <T>  type of instance to validate.
 */
public class DoesNotContainAnyWhitespacesConstraint<T> extends AbstractConstraint<T, CharSequence> {

    public DoesNotContainAnyWhitespacesConstraint() {
        super(DefaultMessages.CHARSEQUENCE_DOES_NOT_CONTAIN_ANY_WHITESPACES);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return !Strings.isNullOrEmpty(validationContext.getPropertyValue()) && !Strings.containsWhitespaces(validationContext.getPropertyValue());
    }
}
