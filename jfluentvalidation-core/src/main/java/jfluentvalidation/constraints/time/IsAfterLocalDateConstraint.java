package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.time.LocalDate;

public class IsAfterLocalDateConstraint implements Constraint<LocalDate> {

    private final LocalDate other;

    public IsAfterLocalDateConstraint(LocalDate other) {
        this.other = other;
    }

    @Override
    public boolean isValid(LocalDate instance) {
        return instance.isAfter(other);
    }
}
