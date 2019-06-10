package jfluentvalidation.constraints.array.length;

import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class CharArrayMinimumLengthConstraint<T> extends AbstractConstraint<T, char[]> {

    private final int min;

    public CharArrayMinimumLengthConstraint(int min) {
        super(DefaultMessages.ARRAY_MINIMUM_LENGTH);
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.min(len, min);
    }
}
