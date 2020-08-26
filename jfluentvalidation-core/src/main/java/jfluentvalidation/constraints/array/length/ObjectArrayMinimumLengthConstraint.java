package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ObjectArrayMinimumLengthConstraint<T, E> extends AbstractConstraint<T, E[]> {

    private final int min;

    public ObjectArrayMinimumLengthConstraint(int min) {
        super(DefaultMessages.ARRAY_MINIMUM_LENGTH);
        this.min = min;
    }

    @Override
    public boolean isValid(ConstraintContext<T, E[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMinLength(len, min);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, E[]> context) {
        context.getMessageContext().appendArgument("min", min);
    }
}
