package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ShortArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, short[]> {

    private final int max;

    public ShortArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(ConstraintContext<T, short[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMaxLength(len, max);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, short[]> context) {
        context.getMessageContext().appendArgument("max", max);
    }
}
