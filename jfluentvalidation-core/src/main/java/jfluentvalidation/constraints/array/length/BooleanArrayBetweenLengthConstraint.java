package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class BooleanArrayBetweenLengthConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private final int min;
    private final int max;

    public BooleanArrayBetweenLengthConstraint(int min, int max) {
        super(DefaultMessages.ARRAY_BETWEEN_LENGTH);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.between(len, min, max);
    }
}
