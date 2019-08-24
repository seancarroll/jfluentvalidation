package jfluentvalidation.constraints.array.isnotnullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNotNullOrEmptyDoubleArrayConstraint<T> extends AbstractConstraint<T, double[]> {

    public IsNotNullOrEmptyDoubleArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().length > 0;
    }
}
