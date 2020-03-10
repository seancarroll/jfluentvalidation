package jfluentvalidation.constraints.time;

import jfluentvalidation.constraints.DefaultMessages;

import java.util.Calendar;
import java.util.function.Supplier;

public class IsInTheFutureCalendarConstraint<T> extends IsAfterCalendarConstraint<T> {
    public IsInTheFutureCalendarConstraint(Supplier<Calendar> other) {
        super(DefaultMessages.TIME_IS_IN_FUTURE, other);
    }
}
