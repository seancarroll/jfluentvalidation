package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

public class IsEmptyConstraint implements Constraint<Iterable<?>> {

    @Override
    public boolean isValid(Iterable<?> value) {
        return Iterables.isEmpty(value);
    }
}
