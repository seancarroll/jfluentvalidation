package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.util.Calendar;
import java.util.function.Supplier;

public class IsInThePastCalendarConstraint<T> extends IsBeforeCalendarConstraint<T> {
    public IsInThePastCalendarConstraint(Supplier<Calendar> other) {
        super(DefaultMessages.TIME_IS_IN_PAST, other);
    }
}
