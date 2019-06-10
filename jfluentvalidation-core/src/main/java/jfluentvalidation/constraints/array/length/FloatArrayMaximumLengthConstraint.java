package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class FloatArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, float[]> {

    private final int max;

    public FloatArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
