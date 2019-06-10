package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ShortArrayMinimumLengthConstraint<T> extends AbstractConstraint<T, short[]> {

    private final int min;

    public ShortArrayMinimumLengthConstraint(int min) {
        super(DefaultMessages.ARRAY_MINIMUM_LENGTH);
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
