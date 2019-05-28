package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class ObjectArrayMinimumLengthConstraint<T> implements Constraint<T, Object[]> {

    private final int min;

    public ObjectArrayMinimumLengthConstraint(int min) {
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
