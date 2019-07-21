package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class IntArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, int[]> {

    private final int max;

    public IntArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMaxLength(len, max);
    }
}
