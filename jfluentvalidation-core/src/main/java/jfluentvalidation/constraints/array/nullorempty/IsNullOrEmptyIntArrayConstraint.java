package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class IsNullOrEmptyIntArrayConstraint<T> extends AbstractConstraint<T, int[]> {

    public IsNullOrEmptyIntArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, int[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
