package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.OffsetDateTime;

public class IsAfterOrEqualOffsetDateTimeConstraint implements Constraint<OffsetDateTime> {

    private final OffsetDateTime other;

    public IsAfterOrEqualOffsetDateTimeConstraint(OffsetDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(OffsetDateTime instance) {
        return !instance.isBefore(other);
    }
}
