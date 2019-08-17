package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNullOrEmptyFloatArrayConstraint<T> extends AbstractConstraint<T, float[]> {

    public IsNullOrEmptyFloatArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
