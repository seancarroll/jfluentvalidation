package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ContainsFloatConstraint<T> extends AbstractConstraint<T, float[]> {

    private final float element;

    public ContainsFloatConstraint(float element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(ConstraintContext<T, float[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.contains(context.getPropertyValue(), element);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, float[]> context) {
        context.getMessageContext().appendArgument("element", element);
    }
}
