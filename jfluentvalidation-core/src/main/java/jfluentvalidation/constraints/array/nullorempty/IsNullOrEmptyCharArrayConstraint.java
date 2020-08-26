package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class IsNullOrEmptyCharArrayConstraint<T> extends AbstractConstraint<T, char[]> {

    public IsNullOrEmptyCharArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, char[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
