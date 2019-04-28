package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks if a CharSequence is empty (""), null or whitespace only.
 * @param <T> type of instance to validate
 */
public class IsBlankConstraint<T> implements Constraint<T, CharSequence> {

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        int length;
        if (validationContext.getPropertyValue() == null || (length = validationContext.getPropertyValue().length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(validationContext.getPropertyValue().charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
