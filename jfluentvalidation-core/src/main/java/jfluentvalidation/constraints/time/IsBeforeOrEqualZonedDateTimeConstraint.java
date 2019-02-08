package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.ZonedDateTime;

public class IsBeforeOrEqualZonedDateTimeConstraint implements Constraint<ZonedDateTime> {

    private final ZonedDateTime other;

    public IsBeforeOrEqualZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(ZonedDateTime instance) {
        return !instance.isAfter(other);
    }
}
