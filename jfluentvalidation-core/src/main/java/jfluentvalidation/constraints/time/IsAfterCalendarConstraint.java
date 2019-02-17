package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.util.Calendar;

public class IsAfterCalendarConstraint implements Constraint<Calendar> {

    private final Calendar other;

    public IsAfterCalendarConstraint(Calendar other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(Calendar value) {
        return value.after(other);
    }
}
