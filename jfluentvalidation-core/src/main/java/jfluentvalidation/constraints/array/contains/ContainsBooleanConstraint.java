package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ContainsBooleanConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private final boolean element;

    public ContainsBooleanConstraint(boolean element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(ConstraintContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.contains(context.getPropertyValue(), element);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, boolean[]> context) {
        context.getMessageContext().appendArgument("element", element);
    }
}
