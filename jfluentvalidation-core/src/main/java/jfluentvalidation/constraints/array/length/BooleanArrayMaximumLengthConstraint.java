package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class BooleanArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private final int max;

    public BooleanArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
