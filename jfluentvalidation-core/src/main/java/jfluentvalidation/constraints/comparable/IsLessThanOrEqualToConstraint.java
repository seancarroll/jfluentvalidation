package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsLessThanOrEqualToConstraint implements Constraint<Comparable> {

    private final Comparable other;

    public IsLessThanOrEqualToConstraint(Comparable other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Comparable value) {
        return value.compareTo(other) <= 0;
    }
}
