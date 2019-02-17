package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.time.LocalDateTime;

public class IsAfterOrEqualLocalDateTimeConstraint implements Constraint<LocalDateTime> {

    private final LocalDateTime other;

    public IsAfterOrEqualLocalDateTimeConstraint(LocalDateTime other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(LocalDateTime value) {
        return !value.isBefore(other);
    }
}
