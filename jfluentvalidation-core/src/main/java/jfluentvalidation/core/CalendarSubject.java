package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterCalendarConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualCalendarConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.util.Calendar;

/**
 *
 * @param <T>  the type of the instance
 */
public class CalendarSubject<T>
    extends AbstractComparableSubject<CalendarSubject<T>, T, Calendar> {

    public CalendarSubject(PropertyRule<T, Calendar> rule) {
        super(CalendarSubject.class, rule);
    }

    public CalendarSubject isBefore(Calendar other) {
        rule.addConstraint(new IsBeforeCalendarConstraint<>(other));
        return myself;
    }

    public CalendarSubject isBeforeOrEqualTo(Calendar other) {
        rule.addConstraint(new IsBeforeOrEqualCalendarConstraint<>(other));
        return myself;
    }

    public CalendarSubject isAfter(Calendar other) {
        rule.addConstraint(new IsAfterCalendarConstraint<>(other));
        return myself;
    }

    public CalendarSubject isAfterOrEqualTo(Calendar other) {
        rule.addConstraint(new IsAfterOrEqualCalendarConstraint<>(other));
        return myself;
    }

    public CalendarSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(Calendar.getInstance());
    }

    public CalendarSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqualTo(Calendar.getInstance());
    }

    public CalendarSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(Calendar.getInstance());
    }

    public CalendarSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqualTo(Calendar.getInstance());
    }

}
