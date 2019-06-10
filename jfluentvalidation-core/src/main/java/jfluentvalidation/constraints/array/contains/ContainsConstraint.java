package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

public class ContainsConstraint<T, A> extends AbstractConstraint<T, A[]> {

    private final A element;

    public ContainsConstraint(A element) {
        super(DefaultMessages.ARRAY_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, A[]> context) {
        return MoreArrays.contains2(context.getPropertyValue(), element);
    }
}
