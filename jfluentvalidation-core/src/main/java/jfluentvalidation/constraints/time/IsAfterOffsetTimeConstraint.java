package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.OffsetTime;

public class IsAfterOffsetTimeConstraint implements Constraint<OffsetTime> {

    private final OffsetTime other;

    public IsAfterOffsetTimeConstraint(OffsetTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(OffsetTime instance) {
        return instance.isAfter(other);
    }
}
