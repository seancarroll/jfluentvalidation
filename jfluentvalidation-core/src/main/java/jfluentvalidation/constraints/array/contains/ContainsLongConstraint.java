package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ContainsLongConstraint<T> extends AbstractConstraint<T, long[]> {

    private final long element;

    public ContainsLongConstraint(long element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        return MoreArrays.contains(context.getPropertyValue(), element);
    }
}
