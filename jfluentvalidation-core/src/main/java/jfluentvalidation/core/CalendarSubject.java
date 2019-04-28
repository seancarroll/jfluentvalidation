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
    extends Subject<CalendarSubject<T>, T, Calendar>
    implements ComparableSubject<CalendarSubject<T>, T, Calendar> {

    public CalendarSubject(PropertyRule<T, Calendar> rule) {
        super(CalendarSubject.class, rule);
    }

    public CalendarSubject isBefore(Calendar other) {
        rule.addConstraint(new IsBeforeCalendarConstraint(other));
        return myself;
    }

    public CalendarSubject isBeforeOrEqual(Calendar other) {
        rule.addConstraint(new IsBeforeOrEqualCalendarConstraint(other));
        return myself;
    }

    public CalendarSubject isAfter(Calendar other) {
        rule.addConstraint(new IsAfterCalendarConstraint(other));
        return myself;
    }

    public CalendarSubject isAfterOrEqual(Calendar other) {
        rule.addConstraint(new IsAfterOrEqualCalendarConstraint(other));
        return myself;
    }

    public CalendarSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(Calendar.getInstance());
    }

    public CalendarSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(Calendar.getInstance());
    }

    public CalendarSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(Calendar.getInstance());
    }

    public CalendarSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(Calendar.getInstance());
    }

    @Override
    public CalendarSubject isEqualAccordingToCompareTo(Calendar other) {
        return myself;
    }

    @Override
    public CalendarSubject isNotEqualAccordingToCompareTo(Calendar other) {
        return myself;
    }

    @Override
    public CalendarSubject isLessThan(Calendar other) {
        return myself;
    }

    @Override
    public CalendarSubject isLessThanOrEqualTo(Calendar other) {
        return myself;
    }

    @Override
    public CalendarSubject isGreaterThan(Calendar other) {
        return myself;
    }

    @Override
    public CalendarSubject isGreaterThanOrEqualTo(Calendar other) {
        return myself;
    }

    @Override
    public CalendarSubject isBetween(Calendar startInclusive, Calendar endInclusive) {
        return myself;
    }

    @Override
    public CalendarSubject isStrictlyBetween(Calendar startExclusive, Calendar endExclusive) {
        return myself;
    }

    @Override
    public CalendarSubject isBetween(Calendar start, Calendar end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public CalendarSubject isNotBetween(Calendar startInclusive, Calendar endInclusive) {
        return myself;
    }

    @Override
    public CalendarSubject isNotBetween(Calendar start, Calendar end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
