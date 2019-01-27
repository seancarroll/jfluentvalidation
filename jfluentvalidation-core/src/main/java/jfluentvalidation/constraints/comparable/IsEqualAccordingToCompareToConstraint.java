package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsEqualAccordingToCompareToConstraint implements Constraint<Comparable> {

    // A raw type is necessary because we can't make assumptions on object to be compared.
    @SuppressWarnings("rawtypes")
    private final Comparable other;

    public IsEqualAccordingToCompareToConstraint(Comparable other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Comparable instance) {
        return instance.compareTo(other) == 0;
    }
}
