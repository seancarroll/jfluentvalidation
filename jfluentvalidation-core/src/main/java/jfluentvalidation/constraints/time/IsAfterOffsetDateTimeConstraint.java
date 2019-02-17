package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.OffsetDateTime;

public class IsAfterOffsetDateTimeConstraint implements Constraint<OffsetDateTime> {

    private final OffsetDateTime other;

    public IsAfterOffsetDateTimeConstraint(OffsetDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(OffsetDateTime value) {
        return value.isAfter(other);
    }
}
