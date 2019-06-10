package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class LongArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, long[]> {

    private final int max;

    public LongArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
