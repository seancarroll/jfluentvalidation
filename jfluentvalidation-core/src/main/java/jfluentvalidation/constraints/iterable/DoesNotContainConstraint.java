package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

public class DoesNotContainConstraint<T> implements Constraint<Iterable<? super T>> {

    private final T element;

    public DoesNotContainConstraint(T element) {
        this.element = element;
    }

    @Override
    public boolean isValid(Iterable<? super T> value) {
        return !Iterables.contains(value, element);
    }
}
