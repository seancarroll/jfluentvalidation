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
public class ContainsOnlyDigitsConstraint<T> extends AbstractConstraint<T, CharSequence> {

    public ContainsOnlyDigitsConstraint() {
        super(DefaultMessages.CHARSEQUENCE_CONTAINS_ONLY_DIGITS);
    }

    // TODO: null/empty
    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        if (Strings.isNullOrEmpty(validationContext.getPropertyValue())) {
            return false;
        }

        // instance.chars().allMatch(Character::isDigit)

        // TODO: should we return index of non-digit characters?
        for (int i = 0; i < validationContext.getPropertyValue().length(); i++) {
            if (!Character.isDigit(validationContext.getPropertyValue().charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
