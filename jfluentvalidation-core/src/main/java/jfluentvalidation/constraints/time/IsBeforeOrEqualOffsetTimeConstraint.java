package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.OffsetTime;

public class IsBeforeOrEqualOffsetTimeConstraint implements Constraint<OffsetTime> {

    private final OffsetTime other;

    public IsBeforeOrEqualOffsetTimeConstraint(OffsetTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(OffsetTime instance) {
        return !instance.isAfter(other);
    }
}
