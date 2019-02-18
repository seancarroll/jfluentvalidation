package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import javax.annotation.Nonnull;

public class IsEqualAccordingToCompareToConstraint implements Constraint<Comparable> {

    private final Comparable<?> other;

    public IsEqualAccordingToCompareToConstraint(@Nonnull Comparable<?> other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(Comparable value) {
        return value.compareTo(other) == 0;
    }
}
