package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class IntArrayMinimumLengthConstraint<T> implements Constraint<T, int[]> {

    private final int min;

    public IntArrayMinimumLengthConstraint(int min) {
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
