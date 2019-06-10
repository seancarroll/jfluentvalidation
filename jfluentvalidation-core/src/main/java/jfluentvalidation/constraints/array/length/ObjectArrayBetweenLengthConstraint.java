package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ObjectArrayBetweenLengthConstraint<T> extends AbstractConstraint<T, Object[]> {

    private final int min;
    private final int max;

    public ObjectArrayBetweenLengthConstraint(int min, int max) {
        super(DefaultMessages.ARRAY_BETWEEN_LENGTH);
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.between(len, min, max);
    }
}
