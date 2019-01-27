package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsGreaterThanOrEqualToConstraint implements Constraint<Comparable> {

    private final Comparable other;

    public IsGreaterThanOrEqualToConstraint(Comparable other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Comparable instance) {
        return instance.compareTo(other) >= 0;
    }
}
