package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsNotEqualAccordingToCompareToConstraint<T extends Comparable<T>> implements Constraint<T> {

    private final T other;

    public IsNotEqualAccordingToCompareToConstraint(T other) {
        this.other = other;
    }

    @Override
    public boolean isValid(T value) {
        return value.compareTo(other) != 0;
    }
}
