package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.OffsetTime;

public class IsAfterOrEqualOffsetTimeConstraint implements Constraint<OffsetTime> {

    private final OffsetTime other;

    public IsAfterOrEqualOffsetTimeConstraint(OffsetTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(OffsetTime value) {
        return !value.isBefore(other);
    }
}
