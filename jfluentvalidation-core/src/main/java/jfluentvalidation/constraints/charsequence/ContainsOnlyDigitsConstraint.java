package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated contains only digits.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsOnlyDigitsConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    public ContainsOnlyDigitsConstraint() {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_ONLY_DIGITS);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (Strings.isNullOrEmpty(context.getPropertyValue())) {
            return false;
        }

        return context.getPropertyValue().chars().allMatch(Character::isDigit);
    }
}
