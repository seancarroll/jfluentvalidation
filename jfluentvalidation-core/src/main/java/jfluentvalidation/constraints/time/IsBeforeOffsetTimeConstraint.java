package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.time.OffsetTime;

public class IsBeforeOffsetTimeConstraint implements Constraint<OffsetTime> {

    private final OffsetTime other;

    public IsBeforeOffsetTimeConstraint(OffsetTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(OffsetTime value) {
        return value.isBefore(other);
    }
}
