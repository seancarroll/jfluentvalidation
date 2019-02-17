package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.util.Calendar;

public class IsAfterOrEqualCalendarConstraint implements Constraint<Calendar> {

    private final Calendar other;

    public IsAfterOrEqualCalendarConstraint(Calendar other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Calendar value) {
        return !value.before(other);
    }
}
