package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class IsNullOrEmptyShortArrayConstraint<T> extends AbstractConstraint<T, short[]> {

    public IsNullOrEmptyShortArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, short[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
