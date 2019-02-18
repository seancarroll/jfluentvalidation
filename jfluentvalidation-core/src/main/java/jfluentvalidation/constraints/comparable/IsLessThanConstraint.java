package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import javax.annotation.Nonnull;

public class IsLessThanConstraint implements Constraint<Comparable<?>> {

    private final Comparable<?> other;

    public IsLessThanConstraint(@Nonnull Comparable<?> other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(Comparable<?> value) {
        return false;
    }
}
