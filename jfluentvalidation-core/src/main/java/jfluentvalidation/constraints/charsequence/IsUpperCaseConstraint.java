package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import static jfluentvalidation.common.Strings.isUpperCase;

/**
 * Check that the given {@code CharSequence} being validated contains only uppercase characters.
 *
 * @param <T>  type of instance to validate
 */
public class IsUpperCaseConstraint<T> extends AbstractConstraint<T, CharSequence> {

    public IsUpperCaseConstraint() {
        super(DefaultMessages.CHARSEQUENCE_IS_UPPER_CASE);
    }

    @Override
    public boolean isValid(RuleContext<T, CharSequence> validationContext) {
        // TODO: should this use locale?
        CharSequence val = validationContext.getPropertyValue();
        if (val == null) {
            return true;
        }

        return isUpperCase(val);
    }

}
