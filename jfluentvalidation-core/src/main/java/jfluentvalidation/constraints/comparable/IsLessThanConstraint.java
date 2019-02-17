package jfluentvalidation.constraints.comparable;

import jfluentvalidation.constraints.Constraint;

public class IsLessThanConstraint implements Constraint<Comparable<?>> {

    private final Comparable<?> other;

    public IsLessThanConstraint(Comparable<?> other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Comparable<?> value) {
        return false;
    }
}
