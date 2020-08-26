package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class IsNullOrEmptyFloatArrayConstraint<T> extends AbstractConstraint<T, float[]> {

    public IsNullOrEmptyFloatArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, float[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
