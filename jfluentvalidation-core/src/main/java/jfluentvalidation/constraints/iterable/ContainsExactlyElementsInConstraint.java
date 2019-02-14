package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

public class ContainsExactlyElementsInConstraint implements Constraint<Iterable<?>> {

    private final Iterable<?> expected;

    public ContainsExactlyElementsInConstraint(Iterable<?> expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        return false;
    }
}
