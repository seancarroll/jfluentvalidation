package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

public class IsNotEmptyConstraint implements Constraint<Iterable<?>> {

    @Override
    public boolean isValid(Iterable<?> instance) {
        return false;
    }
}
