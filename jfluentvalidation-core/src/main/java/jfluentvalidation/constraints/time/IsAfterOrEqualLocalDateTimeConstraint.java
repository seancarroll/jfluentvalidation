package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalDateTime;

public class IsAfterOrEqualLocalDateTimeConstraint implements Constraint<LocalDateTime> {

    private final LocalDateTime other;

    public IsAfterOrEqualLocalDateTimeConstraint(LocalDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalDateTime value) {
        return !value.isBefore(other);
    }
}
