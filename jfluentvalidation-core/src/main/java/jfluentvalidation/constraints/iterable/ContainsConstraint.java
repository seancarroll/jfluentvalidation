package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class ContainsConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    private final P element;

    public ContainsConstraint(P element) {
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return Iterables.contains(context.getPropertyValue(), element);
    }
}
