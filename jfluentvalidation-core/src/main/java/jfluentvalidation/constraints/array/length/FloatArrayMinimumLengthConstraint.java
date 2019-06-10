package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class FloatArrayMinimumLengthConstraint<T> extends AbstractConstraint<T, float[]> {

    private final int min;

    public FloatArrayMinimumLengthConstraint(int min) {
        super(DefaultMessages.ARRAY_MINIMUM_LENGTH);
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
