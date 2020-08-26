package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ContainsLongConstraint<T> extends AbstractConstraint<T, long[]> {

    private final long element;

    public ContainsLongConstraint(long element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(ConstraintContext<T, long[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.contains(context.getPropertyValue(), element);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, long[]> context) {
        context.getMessageContext().appendArgument("element", element);
    }
}
