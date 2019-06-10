package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated contains one or more whitespace characters.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsWhitespacesConstraint<T> extends AbstractConstraint<T, CharSequence> {

    public ContainsWhitespacesConstraint() {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_WHITESPACES);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return !Strings.isNullOrEmpty(validationContext.getPropertyValue()) && Strings.containsWhitespaces(validationContext.getPropertyValue());
    }
}
