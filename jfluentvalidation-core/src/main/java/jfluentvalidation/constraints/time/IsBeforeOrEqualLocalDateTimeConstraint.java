package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalDateTime;

public class IsBeforeOrEqualLocalDateTimeConstraint implements Constraint<LocalDateTime> {

    private final LocalDateTime other;

    public IsBeforeOrEqualLocalDateTimeConstraint(LocalDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalDateTime instance) {
        return !instance.isAfter(other);
    }
}
