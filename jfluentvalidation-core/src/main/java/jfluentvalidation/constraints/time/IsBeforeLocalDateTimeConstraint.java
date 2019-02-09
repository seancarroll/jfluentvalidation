package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalDateTime;

public class IsBeforeLocalDateTimeConstraint implements Constraint<LocalDateTime> {

    private final LocalDateTime other;

    public IsBeforeLocalDateTimeConstraint(LocalDateTime other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalDateTime instance) {
        return instance.isBefore(other);
    }
}