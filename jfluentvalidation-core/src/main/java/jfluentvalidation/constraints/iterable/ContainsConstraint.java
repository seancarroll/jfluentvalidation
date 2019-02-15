package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

public class ContainsConstraint implements Constraint<Iterable<?>> {

    private final Object element;

    public ContainsConstraint(Object element) {
        this.element = element;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        return Iterables.contains(instance, element);
    }
}
