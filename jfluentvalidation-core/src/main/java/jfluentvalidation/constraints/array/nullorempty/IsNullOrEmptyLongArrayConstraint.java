package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class IsNullOrEmptyLongArrayConstraint<T> extends AbstractConstraint<T, long[]> {

    public IsNullOrEmptyLongArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, long[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
