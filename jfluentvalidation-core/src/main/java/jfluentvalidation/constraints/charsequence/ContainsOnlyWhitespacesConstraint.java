package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated consists of one or more whitespace characters.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsOnlyWhitespacesConstraint<T> implements Constraint<T, CharSequence> {

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        return !Strings.isNullOrEmpty(validationContext.getPropertyValue()) && Strings.containsOnlyWhitespace(validationContext.getPropertyValue());
    }
}
