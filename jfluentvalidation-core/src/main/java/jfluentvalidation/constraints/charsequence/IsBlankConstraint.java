package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks if a CharSequence is empty (""), null or whitespace only.
 */
public class IsBlankConstraint<T> implements Constraint<T, CharSequence> {

    // TODO: update logic to not be a copy of commons-lang
    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        int strLen;
        if (validationContext.getPropertyValue() == null || (strLen = validationContext.getPropertyValue().length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(validationContext.getPropertyValue().charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
