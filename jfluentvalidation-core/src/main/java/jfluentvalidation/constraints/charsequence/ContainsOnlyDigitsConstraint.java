package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.common.Strings;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code CharSequence} being validated contains only digits.
 *
 * @param <T>  type of instance to validate.
 */
public class ContainsOnlyDigitsConstraint<T> implements Constraint<T, CharSequence> {

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
