package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;

import java.util.Calendar;

public class IsBeforeOrEqualCalendarConstraint implements Constraint<Calendar> {

    private final Calendar other;

    public IsBeforeOrEqualCalendarConstraint(Calendar other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(Calendar value) {
        return !value.after(other);
    }
}
