package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 * Check that the given {@code CharSequence} being validated is {@code Null}, empty or contains only non-whitespace characters.
 *
 * @param <T>  type of instance to validate.
 */
public class DoesNotContainAnyWhitespacesConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    public DoesNotContainAnyWhitespacesConstraint() {
        super(DefaultMessages.CHARSEQUENCE_DOES_NOT_CONTAIN_ANY_WHITESPACES);
    }

    @Override
    public boolean isValid(ConstraintContext<T, A> cont) {
        return Strings.isNullOrEmpty(cont.getPropertyValue()) || !Strings.containsWhitespaces(cont.getPropertyValue());
    }
}
