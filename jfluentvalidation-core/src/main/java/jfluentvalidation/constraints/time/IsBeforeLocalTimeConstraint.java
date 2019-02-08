package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalTime;

public class IsBeforeLocalTimeConstraint implements Constraint<LocalTime> {

    private final LocalTime other;

    public IsBeforeLocalTimeConstraint(LocalTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalTime instance) {
        return instance.isBefore(other);
    }
}
