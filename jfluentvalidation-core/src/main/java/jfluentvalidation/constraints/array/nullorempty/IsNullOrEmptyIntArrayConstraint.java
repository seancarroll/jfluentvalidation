package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNullOrEmptyIntArrayConstraint<T> extends AbstractConstraint<T, int[]> {

    public IsNullOrEmptyIntArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
