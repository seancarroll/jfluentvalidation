package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class DoubleArrayMaximumLengthConstraint<T> implements Constraint<T, double[]> {

    private final int max;

    public DoubleArrayMaximumLengthConstraint(int max) {
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
