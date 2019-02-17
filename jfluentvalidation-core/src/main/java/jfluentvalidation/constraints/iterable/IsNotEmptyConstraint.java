package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

public class IsNotEmptyConstraint implements Constraint<Iterable<?>> {

    @Override
    public boolean isValid(Iterable<?> value) {
        return !Iterables.isEmpty(value);
    }
}
