package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.time.OffsetDateTime;

public class IsAfterOrEqualOffsetDateTimeConstraint implements Constraint<OffsetDateTime> {

    private final OffsetDateTime other;

    public IsAfterOrEqualOffsetDateTimeConstraint(OffsetDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(OffsetDateTime value) {
        return !value.isBefore(other);
    }
}
