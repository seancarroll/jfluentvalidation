package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyBooleanArrayConstraint<T> extends AbstractConstraint<T, boolean[]> {

    public IsNotEmptyBooleanArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length > 0;
    }
}
