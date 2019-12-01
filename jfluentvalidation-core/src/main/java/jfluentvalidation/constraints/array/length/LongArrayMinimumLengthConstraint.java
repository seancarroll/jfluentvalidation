package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class LongArrayMinimumLengthConstraint<T> extends AbstractConstraint<T, long[]> {

    private final int min;

    public LongArrayMinimumLengthConstraint(int min) {
        super(DefaultMessages.ARRAY_MINIMUM_LENGTH);
        this.min = min;
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMinLength(len, min);
    }

    @Override
    public void addParametersToContext(RuleContext<T, long[]> context) {
        context.getMessageContext().appendArgument("min", min);
    }
}
