package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class CharArrayMaximumLengthConstraint<T> extends AbstractConstraint<T, char[]> {

    private final int max;

    public CharArrayMaximumLengthConstraint(int max) {
        super(DefaultMessages.ARRAY_MAXIMUM_LENGTH);
        this.max = max;
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return MoreArrays.hasMaxLength(len, max);
    }

    @Override
    public void addParametersToContext(RuleContext<T, char[]> context) {
        context.getMessageContext().appendArgument("max", max);
    }
}
