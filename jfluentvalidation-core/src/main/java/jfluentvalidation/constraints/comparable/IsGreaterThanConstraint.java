package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsGreaterThanConstraint implements Constraint<Comparable> {

    private final Comparable other;

    public IsGreaterThanConstraint(Comparable other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Comparable instance) {
        return instance.compareTo(other) > 0;
    }
}
