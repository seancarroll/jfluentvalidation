package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class LongArrayMinimumLengthConstraint<T> implements Constraint<T, long[]> {

    private final int min;

    public LongArrayMinimumLengthConstraint(int min) {
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
