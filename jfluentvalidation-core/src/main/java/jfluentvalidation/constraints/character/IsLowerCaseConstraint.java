package jfluentvalidation.constraints.character;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import static java.lang.Character.isLowerCase;

/**
 * Check that the given {@code Character} being validated is lowercase.
 *
 * @param <T>  type of instance to validate.
 */
public class IsLowerCaseConstraint<T> extends AbstractConstraint<T, Character> {

    public IsLowerCaseConstraint() {
        super(DefaultMessages.CHARACTER_IS_LOWER_CASE);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Character> validationContext) {
        // TODO: should this use locale?
        Character val = validationContext.getPropertyValue();
        if (val == null) {
            return true;
        }

        return isLowerCase(val);
    }

}
