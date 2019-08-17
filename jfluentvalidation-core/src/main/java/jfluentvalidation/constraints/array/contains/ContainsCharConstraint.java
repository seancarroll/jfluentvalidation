package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ContainsCharConstraint<T> extends AbstractConstraint<T, char[]> {

    private final char element;

    public ContainsCharConstraint(char element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        return MoreArrays.contains(context.getPropertyValue(), element);
    }
}
