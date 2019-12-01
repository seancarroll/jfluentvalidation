package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
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
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMinLength(len, min);
    }

    @Override
    public void addParametersToContext(RuleContext<T, short[]> context) {
        context.getMessageContext().appendArgument("min", min);
    }
}
