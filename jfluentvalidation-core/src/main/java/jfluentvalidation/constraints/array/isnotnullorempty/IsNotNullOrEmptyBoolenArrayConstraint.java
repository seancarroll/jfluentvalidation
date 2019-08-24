package jfluentvalidation.constraints.array.isnotnullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNotNullOrEmptyBoolenArrayConstraint<T> extends AbstractConstraint<T, boolean[]> {

    public IsNotNullOrEmptyBoolenArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY_OR_NULL);
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().length > 0;
    }
}
