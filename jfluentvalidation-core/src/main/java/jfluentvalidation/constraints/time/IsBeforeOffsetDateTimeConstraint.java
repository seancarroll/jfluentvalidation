package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.OffsetDateTime;

public class IsBeforeOffsetDateTimeConstraint implements Constraint<OffsetDateTime> {

    private final OffsetDateTime other;

    public IsBeforeOffsetDateTimeConstraint(OffsetDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(OffsetDateTime value) {
        return value.isBefore(other);
    }
}
