package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ContainsCharConstraint<T> extends AbstractConstraint<T, char[]> {

    private final char element;

    public ContainsCharConstraint(char element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(ConstraintContext<T, char[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.contains(context.getPropertyValue(), element);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, char[]> context) {
        context.getMessageContext().appendArgument("element", element);
    }
}
