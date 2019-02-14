package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

public class HasSizeConstraint implements Constraint<Iterable<?>> {

    private final int expectedSize;

    public HasSizeConstraint(int expectedSize) {
        Ensure.nonnegative(expectedSize, "expectedSize");
        this.expectedSize = expectedSize;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        return Iterables.size(instance) == expectedSize;
    }

}
