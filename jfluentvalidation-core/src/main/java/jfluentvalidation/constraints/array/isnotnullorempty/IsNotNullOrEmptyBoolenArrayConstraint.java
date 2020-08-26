package jfluentvalidation.constraints.array.isnotnullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class IsNotNullOrEmptyBoolenArrayConstraint<T> extends AbstractConstraint<T, boolean[]> {

    public IsNotNullOrEmptyBoolenArrayConstraint() {
        super(DefaultMessages.IS_NOT_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(ConstraintContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().length > 0;
    }
}
