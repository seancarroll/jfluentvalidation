package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNullOrEmptyLongArrayConstraint<T> extends AbstractConstraint<T, long[]> {

    public IsNullOrEmptyLongArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
