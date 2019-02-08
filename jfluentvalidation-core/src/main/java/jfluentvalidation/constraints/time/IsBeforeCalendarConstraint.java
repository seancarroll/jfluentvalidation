package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.util.Calendar;

public class IsBeforeCalendarConstraint implements Constraint<Calendar> {

    private final Calendar other;

    public IsBeforeCalendarConstraint(Calendar other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Calendar instance) {
        return instance.before(other);
    }
}
