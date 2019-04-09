package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class IsEmptyConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return Iterables.isEmpty(context.getPropertyValue());
    }
}
