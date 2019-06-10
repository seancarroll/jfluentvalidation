package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ObjectArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, Object[]> {

    private final int max;

    public ObjectArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.max(len, max);
    }
}
