package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

public class ContainsAllInConstraint implements Constraint<Iterable<?>> {

    private final Iterable<?> expectedIterable;

    public ContainsAllInConstraint(Iterable<?> expectedIterable) {
        this.expectedIterable = expectedIterable;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        return false;
    }
}
