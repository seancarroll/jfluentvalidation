package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.OffsetDateTime;

public class IsBeforeOrEqualOffsetDateTimeConstraint implements Constraint<OffsetDateTime> {

    private final OffsetDateTime other;

    public IsBeforeOrEqualOffsetDateTimeConstraint(OffsetDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(OffsetDateTime value) {
        return !value.isAfter(other);
    }
}
