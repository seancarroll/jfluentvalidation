package jfluentvalidation.constraints.charsequence;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import static jfluentvalidation.common.Strings.isLowerCase;

/**
 * Check that the given {@code CharSequence} being validated contains only lowercase characters.
 *
 * @param <T>  type of instance to validate.
 */
public class IsLowerCaseConstraint<T> extends AbstractConstraint<T, CharSequence> {

    public IsLowerCaseConstraint() {
        super(DefaultMessages.CHARSEQUENCE_IS_LOWER_CASE);
    }

    @Override
    public boolean isValid(ConstraintContext<T, CharSequence> validationContext) {
        // TODO: should this use locale?
        CharSequence val = validationContext.getPropertyValue();
        if (val == null) {
            return true;
        }

        return isLowerCase(val);
    }

}
