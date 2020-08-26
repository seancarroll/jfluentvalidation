package jfluentvalidation.constraints.array.notempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 *
 * @param <T>
 */
public class IsNotEmptyShortArrayConstraint<T> extends AbstractConstraint<T, short[]> {

    public IsNotEmptyShortArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, short[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length > 0;
    }
}
