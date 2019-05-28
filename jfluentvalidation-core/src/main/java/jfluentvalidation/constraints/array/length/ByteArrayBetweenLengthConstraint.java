package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class ByteArrayBetweenLengthConstraint<T> implements Constraint<T, byte[]> {

    private final int min;
    private final int max;

    public ByteArrayBetweenLengthConstraint(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.between(len, min, max);
    }
}
