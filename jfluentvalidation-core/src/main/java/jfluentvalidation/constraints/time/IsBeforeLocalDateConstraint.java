package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.time.LocalDate;

public class IsBeforeLocalDateConstraint implements Constraint<LocalDate> {

    private final LocalDate other;

    public IsBeforeLocalDateConstraint(LocalDate other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(LocalDate value) {
        return value.isBefore(other);
    }
}
