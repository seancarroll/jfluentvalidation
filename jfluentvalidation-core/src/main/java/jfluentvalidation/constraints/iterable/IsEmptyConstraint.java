package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

public class IsEmptyConstraint<T> implements Constraint<Iterable<? super T>> {

    @Override
    public boolean isValid(Iterable<? super T> value) {
        return Iterables.isEmpty(value);
    }
}
