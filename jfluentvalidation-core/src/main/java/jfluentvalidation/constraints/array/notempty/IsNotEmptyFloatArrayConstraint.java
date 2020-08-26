package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyFloatArrayConstraint<T> extends AbstractConstraint<T, float[]> {

    public IsNotEmptyFloatArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, float[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length > 0;
    }
}
