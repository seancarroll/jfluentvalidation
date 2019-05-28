package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class FloatArrayMinimumLengthConstraint<T> implements Constraint<T, float[]> {

    private final int min;

    public FloatArrayMinimumLengthConstraint(int min) {
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
