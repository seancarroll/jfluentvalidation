package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks that the given {@code CharSequence} being validated is a valid email.
 * Note: This validation should not be considered a fully comprehensive check but viewed as a "good enough" for must
 * scenarios.
 * You should use alternative means to actually determine if given {@code CharSequence} is actually valid email.
 *
 * @param <T> type of instance to validate.
 */
public class IsEmailConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    public IsEmailConstraint() {
        super(DefaultMessages.CHARSEQUENCE_IS_EMAIL);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        CharSequence value = context.getPropertyValue();
        if (value == null || value.length() == 0) {
            return true;
        }

        return EmailValidator.isValid(value);
    }

}
