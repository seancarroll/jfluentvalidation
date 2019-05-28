package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class DoubleArrayBetweenLengthConstraint<T> implements Constraint<T, double[]> {

    private final int min;
    private final int max;

    public DoubleArrayBetweenLengthConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.between(len, min, max);
    }
}
