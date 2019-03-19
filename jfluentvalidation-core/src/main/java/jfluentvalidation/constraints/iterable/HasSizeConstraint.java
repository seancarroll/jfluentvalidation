package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

public class HasSizeConstraint<T> implements Constraint<Iterable<? super T>> {

    private final int expectedSize;

    public HasSizeConstraint(int expectedSize) {
        this.expectedSize = Ensure.nonnegative(expectedSize, "expectedSize");
    }

    @Override
    public boolean isValid(Iterable<? super T> value) {
        return Iterables.size(value) == expectedSize;
    }

}
