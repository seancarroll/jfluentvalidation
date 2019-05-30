package jfluentvalidation.constraints.array.contains;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class ContainsConstraint<T, A> implements Constraint<T, A[]> {

    private final A element;

    public ContainsConstraint(A element) {
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, A[]> context) {
        return MoreArrays.contains2(context.getPropertyValue(), element);
    }
}
