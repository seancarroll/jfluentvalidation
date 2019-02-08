package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;

import java.util.Calendar;

public class IsAfterCalendarConstraint implements Constraint<Calendar> {

    private final Calendar other;

    public IsAfterCalendarConstraint(Calendar other) {
        this.other = other;
    }

    @Override
    public boolean isValid(Calendar instance) {
        return instance.after(other);
    }
}
