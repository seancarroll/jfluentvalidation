package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

public class ByteArrayBetweenLengthConstraint<T> extends AbstractConstraint<T, byte[]> {

    private final int min;
    private final int max;
    private final boolean minInclusive;
    private final boolean maxInclusive;

    public ByteArrayBetweenLengthConstraint(int min, int max, boolean minInclusive, boolean maxInclusive) {
        super(DefaultMessages.ARRAY_BETWEEN_LENGTH);
        Ensure.argument(min <= max);
        this.min = min;
        this.max = max;
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    @Override
    public boolean isValid(ConstraintContext<T, byte[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasLengthBetween(len, min, max, minInclusive, maxInclusive);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, byte[]> context) {
        context.getMessageContext().appendArgument("min", min);
        context.getMessageContext().appendArgument("max", max);
        context.getMessageContext().appendArgument("minInclusive", minInclusive);
        context.getMessageContext().appendArgument("maxInclusive", maxInclusive);
    }
}
