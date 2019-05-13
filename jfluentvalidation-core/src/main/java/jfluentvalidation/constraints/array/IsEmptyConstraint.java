package jfluentvalidation.constraints.array;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

public class IsEmptyConstraint<T, E> implements Constraint<T, E> {

    @Override
    public boolean isValid(RuleContext<T, E> context) {
        return false;
    }
}
