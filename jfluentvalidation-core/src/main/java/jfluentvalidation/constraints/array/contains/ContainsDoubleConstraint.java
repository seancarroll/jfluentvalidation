package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ContainsDoubleConstraint<T> extends AbstractConstraint<T, double[]> {

    private final double element;

    public ContainsDoubleConstraint(double element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(ConstraintContext<T, double[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.contains(context.getPropertyValue(), element);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, double[]> context) {
        context.getMessageContext().appendArgument("element", element);
    }
}
