package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalTime;

public class IsAfterOrEqualLocalTimeConstraint implements Constraint<LocalTime> {

    private final LocalTime other;

    public IsAfterOrEqualLocalTimeConstraint(LocalTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalTime value) {
        return !value.isBefore(other);
    }
}
