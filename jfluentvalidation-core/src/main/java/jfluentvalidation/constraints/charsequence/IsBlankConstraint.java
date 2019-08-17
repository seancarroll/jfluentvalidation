package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks if a CharSequence is empty (""), null or whitespace only.
 *
 * @param <T>  type of instance to validate.
 */
public class IsBlankConstraint<T, A extends CharSequence> extends AbstractConstraint<T, A> {

    public IsBlankConstraint() {
        super(DefaultMessages.CHARSEQUENCE_IS_BLANK);
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        int length;
        if (context.getPropertyValue() == null || (length = context.getPropertyValue().length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(context.getPropertyValue().charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
