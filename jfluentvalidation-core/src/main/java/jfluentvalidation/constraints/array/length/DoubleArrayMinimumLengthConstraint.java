package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class DoubleArrayMinimumLengthConstraint<T> extends AbstractConstraint<T, double[]> {

    private final int min;

    public DoubleArrayMinimumLengthConstraint(int min) {
        super(DefaultMessages.ARRAY_MINIMUM_LENGTH);
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
