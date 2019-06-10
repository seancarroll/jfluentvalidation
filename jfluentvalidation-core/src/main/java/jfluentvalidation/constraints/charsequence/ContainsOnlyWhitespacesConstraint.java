package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated consists of one or more whitespace characters.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsOnlyWhitespacesConstraint<T> extends AbstractConstraint<T, CharSequence> {

    public ContainsOnlyWhitespacesConstraint() {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_ONLY_WHITESPACES);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return !Strings.isNullOrEmpty(validationContext.getPropertyValue()) && Strings.containsOnlyWhitespace(validationContext.getPropertyValue());
    }
}
