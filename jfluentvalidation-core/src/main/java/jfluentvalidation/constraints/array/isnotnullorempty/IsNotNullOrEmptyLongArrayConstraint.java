package jfluentvalidation.constraints.array.isnotnullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNotNullOrEmptyLongArrayConstraint<T> extends AbstractConstraint<T, long[]> {

    public IsNotNullOrEmptyLongArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().length > 0;
    }
}
