package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterCalendarConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeCalendarConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualCalendarConstraint;

import java.util.Calendar;
import java.util.function.Function;

public class CalendarSubject
    extends Subject<CalendarSubject, Calendar>
    implements ComparableSubject<CalendarSubject, Calendar> {

    public CalendarSubject(Function propertyFunc, String propertyName) {
        super(Calendar.class, propertyFunc, propertyName);
    }

    public CalendarSubject isBefore(Calendar other) {
        constraints.add(new IsBeforeCalendarConstraint(other));
        return  myself;
    }

    public CalendarSubject isBeforeOrEqual(Calendar other) {
        constraints.add(new IsBeforeOrEqualCalendarConstraint(other));
        return  myself;
    }

    public CalendarSubject isAfter(Calendar other) {
        constraints.add(new IsAfterCalendarConstraint(other));
        return  myself;
    }

    public CalendarSubject isAfterOrEqual(Calendar other) {
        constraints.add(new IsAfterOrEqualCalendarConstraint(other));
        return  myself;
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
        return null;
    }

    @Override
    public CalendarSubject isNotEqualAccordingToCompareTo(Calendar other) {
        return null;
    }

    @Override
    public CalendarSubject isLessThan(Calendar other) {
        return null;
    }

    @Override
    public CalendarSubject isLessThanOrEqualTo(Calendar other) {
        return null;
    }

    @Override
    public CalendarSubject isGreaterThan(Calendar other) {
        return null;
    }

    @Override
    public CalendarSubject isGreaterThanOrEqualTo(Calendar other) {
        return null;
    }

    @Override
    public CalendarSubject isBetween(Calendar startInclusive, Calendar endInclusive) {
        return null;
    }

    @Override
    public CalendarSubject isStrictlyBetween(Calendar startExclusive, Calendar endExclusive) {
        return null;
    }

    @Override
    public CalendarSubject isBetween(Calendar start, Calendar end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public CalendarSubject isNotBetween(Calendar startInclusive, Calendar endInclusive) {
        return null;
    }

    @Override
    public CalendarSubject isNotBetween(Calendar start, Calendar end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
