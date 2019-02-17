package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.ZonedDateTime;

public class IsAfterOrEqualZonedDateTimeConstraint implements Constraint<ZonedDateTime> {

    private final ZonedDateTime other;

    public IsAfterOrEqualZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(ZonedDateTime value) {
        return !value.isBefore(other);
    }
}
