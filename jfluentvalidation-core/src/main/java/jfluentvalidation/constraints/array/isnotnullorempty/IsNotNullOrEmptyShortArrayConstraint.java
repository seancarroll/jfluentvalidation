package jfluentvalidation.constraints.array.isnotnullorempty;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IsNotNullOrEmptyShortArrayConstraint<T> extends AbstractConstraint<T, short[]> {

    public IsNotNullOrEmptyShortArrayConstraint() {
        super(DefaultMessages.IS_NOT_EMPTY);
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        if (context.getPropertyValue() == null) {
            return false;
        }
        return context.getPropertyValue().length > 0;
    }
}
