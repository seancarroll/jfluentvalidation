package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

public class IntArrayBetweenLengthConstraint<T> extends AbstractConstraint<T, int[]> {

    private final int min;
    private final int max;
    private final boolean minInclusive;
    private final boolean maxInclusive;

    public IntArrayBetweenLengthConstraint(int min, int max) {
        this(min, max, true, true);
    }

    public IntArrayBetweenLengthConstraint(int min, int max, boolean minInclusive, boolean maxInclusive) {
        super(DefaultMessages.ARRAY_BETWEEN_LENGTH);
        Ensure.argument(min <= max);
        this.min = min;
        this.max = max;
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    @Override
    public boolean isValid(RuleContext<T, int[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return ArrayLength.between(len, min, max, minInclusive, maxInclusive);
    }
}
