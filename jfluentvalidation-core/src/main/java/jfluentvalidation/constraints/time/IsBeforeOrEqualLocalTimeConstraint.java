package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalTime;

public class IsBeforeOrEqualLocalTimeConstraint implements Constraint<LocalTime> {

    private final LocalTime other;

    public IsBeforeOrEqualLocalTimeConstraint(LocalTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalTime value) {
        return !value.isAfter(other);
    }
}
