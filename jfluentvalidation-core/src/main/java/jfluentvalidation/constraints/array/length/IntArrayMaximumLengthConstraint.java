package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class IntArrayMaximumLengthConstraint<T> implements Constraint<T, int[]> {

    private final int max;

    public IntArrayMaximumLengthConstraint(int max) {
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
