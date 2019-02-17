package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.ZonedDateTime;

public class IsAfterZonedDateTimeConstraint implements Constraint<ZonedDateTime> {

    private final ZonedDateTime other;

    public IsAfterZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(ZonedDateTime value) {
        return value.isAfter(other);
    }
}
