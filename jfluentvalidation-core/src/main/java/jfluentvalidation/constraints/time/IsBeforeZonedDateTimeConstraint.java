package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.ZonedDateTime;

public class IsBeforeZonedDateTimeConstraint implements Constraint<ZonedDateTime> {

    private final ZonedDateTime other;

    public IsBeforeZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(ZonedDateTime instance) {
        return instance.isBefore(other);
    }
}
