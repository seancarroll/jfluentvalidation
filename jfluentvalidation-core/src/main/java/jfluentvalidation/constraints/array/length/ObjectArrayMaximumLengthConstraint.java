package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class ObjectArrayMaximumLengthConstraint<T> implements Constraint<T, Object[]> {

    private final int max;

    public ObjectArrayMaximumLengthConstraint(int max) {
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
