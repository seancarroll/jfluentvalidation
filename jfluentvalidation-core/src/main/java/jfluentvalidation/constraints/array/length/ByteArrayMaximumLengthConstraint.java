package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class ByteArrayMaximumLengthConstraint<T> implements Constraint<T, byte[]> {

    private final int max;

    public ByteArrayMaximumLengthConstraint(int max) {
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
