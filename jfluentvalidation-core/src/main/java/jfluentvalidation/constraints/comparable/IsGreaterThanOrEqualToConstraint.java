package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import javax.annotation.Nonnull;

public class IsGreaterThanOrEqualToConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final T other;

    public IsGreaterThanOrEqualToConstraint(@Nonnull T other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(T value) {
        return value.compareTo(other) >= 0;
    }
}
