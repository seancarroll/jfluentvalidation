package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

public class ContainsObjectConstraint<T, A> extends AbstractConstraint<T, A[]> {

    private final A element;

    public ContainsObjectConstraint(A element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(ConstraintContext<T, A[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return MoreArrays.contains2(context.getPropertyValue(), element);
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, A[]> context) {
        context.getMessageContext().appendArgument("element", element);
    }
}
