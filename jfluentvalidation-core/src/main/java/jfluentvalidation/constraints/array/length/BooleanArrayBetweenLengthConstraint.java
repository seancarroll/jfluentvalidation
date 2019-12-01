package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

public class BooleanArrayBetweenLengthConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private final int min;
    private final int max;
    private final boolean minInclusive;
    private final boolean maxInclusive;

    public BooleanArrayBetweenLengthConstraint(int min, int max) {
        this(min, max, true, true);
    }

    public BooleanArrayBetweenLengthConstraint(int min, int max, boolean minInclusive, boolean maxInclusive) {
        super(DefaultMessages.ARRAY_BETWEEN_LENGTH);
        Ensure.argument(min <= max);
        this.min = min;
        this.max = max;
        this.minInclusive = minInclusive;
        this.maxInclusive = maxInclusive;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return MoreArrays.hasLengthBetween(len, min, max, minInclusive, maxInclusive);
    }

    @Override
    public void addParametersToContext(RuleContext<T, boolean[]> context) {
        context.getMessageContext().appendArgument("min", min);
        context.getMessageContext().appendArgument("max", max);
        context.getMessageContext().appendArgument("minInclusive", minInclusive);
        context.getMessageContext().appendArgument("maxInclusive", maxInclusive);
    }
}
