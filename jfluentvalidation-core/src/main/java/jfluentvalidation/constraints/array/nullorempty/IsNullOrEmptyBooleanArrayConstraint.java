package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNullOrEmptyBooleanArrayConstraint<T> extends AbstractConstraint<T, boolean[]> {

    public IsNullOrEmptyBooleanArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}
