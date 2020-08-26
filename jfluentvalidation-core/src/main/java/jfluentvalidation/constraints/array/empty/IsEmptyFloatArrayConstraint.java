package jfluentvalidation.constraints.array.empty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 *
 * @param <T>
 */
public class IsEmptyFloatArrayConstraint<T> extends AbstractConstraint<T, float[]> {

    public IsEmptyFloatArrayConstraint() {
        super(DefaultMessages.IS_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, float[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length == 0;
    }
}
