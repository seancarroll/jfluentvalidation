package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

public class ObjectArrayBetweenLengthConstraint<T, E> extends AbstractConstraint<T, E[]> {

    private final int min;
    private final int max;
    private final boolean minInclusive;
    private final boolean maxInclusive;

    public ObjectArrayBetweenLengthConstraint(int min, int max, boolean minInclusive, boolean maxInclusive) {
        super(DefaultMessages.ARRAY_BETWEEN_LENGTH);
        Ensure.argument(min <= max);
        this.min = min;
        this.max = max;
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    @Override
    public boolean isValid(ConstraintContext<T, E[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasLengthBetween(len, min, max, minInclusive, maxInclusive);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, E[]> context) {
        context.getMessageContext().appendArgument("min", min);
        context.getMessageContext().appendArgument("max", max);
        context.getMessageContext().appendArgument("minInclusive", minInclusive);
        context.getMessageContext().appendArgument("maxInclusive", maxInclusive);
    }
}
