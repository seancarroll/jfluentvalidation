package jfluentvalidation.constraints.array.nullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNullOrEmptyByteArrayConstraint<T> extends AbstractConstraint<T, byte[]> {

    public IsNullOrEmptyByteArrayConstraint() {
        super(DefaultMessages.IS_NULL_OR_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        return context.getPropertyValue() == null || context.getPropertyValue().length == 0;
    }
}