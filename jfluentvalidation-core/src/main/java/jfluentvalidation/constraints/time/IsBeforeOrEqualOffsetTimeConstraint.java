package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.time.OffsetTime;

public class IsBeforeOrEqualOffsetTimeConstraint implements Constraint<OffsetTime> {

    private final OffsetTime other;

    public IsBeforeOrEqualOffsetTimeConstraint(OffsetTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(OffsetTime value) {
        return !value.isAfter(other);
    }
}
