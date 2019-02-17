package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalDate;

public class IsBeforeOrEqualLocalDateConstraint implements Constraint<LocalDate> {

    private final LocalDate other;

    public IsBeforeOrEqualLocalDateConstraint(LocalDate other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalDate value) {
        return !value.isAfter(other);
    }
}
