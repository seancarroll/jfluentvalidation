package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

import java.util.Collection;

public class IsEmptyConstraint implements Constraint<Iterable<?>> {

    @Override
    public boolean isValid(Iterable<?> instance) {
        if (instance instanceof Collection) {
            return ((Collection)instance).isEmpty();
        } else {
            return !instance.iterator().hasNext();
        }
    }
}
