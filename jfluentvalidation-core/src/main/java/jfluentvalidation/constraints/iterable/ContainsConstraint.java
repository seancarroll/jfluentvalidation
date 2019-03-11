package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

public class ContainsConstraint<T> implements Constraint<Iterable<? super T>> {

    private final T element;

    public ContainsConstraint(T element) {
        this.element = element;
    }

    @Override
    public boolean isValid(Iterable<? super T> value) {
        return Iterables.contains(value, element);
    }
}
