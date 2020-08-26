package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class DoubleArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, double[]> {

    private final int max;

    public DoubleArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(ConstraintContext<T, double[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMaxLength(len, max);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, double[]> context) {
        context.getMessageContext().appendArgument("max", max);
    }
}
