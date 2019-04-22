package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Calendar;

public class IsBeforeCalendarConstraint<T> implements Constraint<T, Calendar> {

    private final Calendar other;

    public IsBeforeCalendarConstraint(Calendar other) {
        this.other = Ensure.notNull(other);
    }

    @Override
    public boolean isValid(RuleContext<T, Calendar> context) {
        return context.getPropertyValue().before(other);
    }
}
