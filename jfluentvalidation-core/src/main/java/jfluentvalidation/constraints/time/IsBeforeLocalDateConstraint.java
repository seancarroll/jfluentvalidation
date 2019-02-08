package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalDate;

public class IsBeforeLocalDateConstraint implements Constraint<LocalDate> {

    private final LocalDate other;

    public IsBeforeLocalDateConstraint(LocalDate other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalDate instance) {
        return instance.isBefore(other);
    }
}
