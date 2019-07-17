package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ContainsBooleanConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private final boolean element;

    public ContainsBooleanConstraint(boolean element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        return MoreArrays.contains(context.getPropertyValue(), element);
    }
}
