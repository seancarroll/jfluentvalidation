package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class FloatArrayBetweenLengthConstraint<T> extends AbstractConstraint<T, float[]> {

    private final int min;
    private final int max;

    public FloatArrayBetweenLengthConstraint(int min, int max) {
        super(DefaultMessages.ARRAY_BETWEEN_LENGTH);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.between(len, min, max);
    }
}
