package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class LongArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, long[]> {

    private final int max;

    public LongArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(ConstraintContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMaxLength(len, max);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, long[]> context) {
        context.getMessageContext().appendArgument("max", max);
    }
}
