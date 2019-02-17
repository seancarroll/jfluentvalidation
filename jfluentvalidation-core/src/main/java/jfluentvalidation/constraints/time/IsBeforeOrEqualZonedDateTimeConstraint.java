package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.time.ZonedDateTime;

public class IsBeforeOrEqualZonedDateTimeConstraint implements Constraint<ZonedDateTime> {

    private final ZonedDateTime other;

    public IsBeforeOrEqualZonedDateTimeConstraint(ZonedDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(ZonedDateTime value) {
        return !value.isAfter(other);
    }
}
