package jfluentvalidation.constraints.character;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

import static java.lang.Character.isUpperCase;

/**
 * Check that the given {@code Charactor} being validated is uppercase.
 *
 * @param <T>  type of instance to validate
 */
public class IsUpperCaseConstraint<T> extends AbstractConstraint<T, Character> {

    public IsUpperCaseConstraint() {
        super(DefaultMessages.CHARACTER_IS_UPPER_CASE);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Character> validationContext) {
        // TODO: should this use locale?
        Character val = validationContext.getPropertyValue();
        if (val == null) {
            return true;
        }

        return isUpperCase(val);
    }

}
