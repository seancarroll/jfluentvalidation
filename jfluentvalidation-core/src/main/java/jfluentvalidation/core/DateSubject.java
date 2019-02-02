package jfluentvalidation.core;

import java.util.Date;
import java.util.function.Function;

// rather than isLessThan, isGreaterThan etc should we have isBefore, isAfter, etc? How to separate from other Comparable?
// NumberComparable vs TemporalComparable/some other name
public class DateSubject extends Subject<DateSubject, Date> implements ComparableSubject<DateSubject, Date> {

    public DateSubject(Function propertyFunc, String propertyName) {
        super(DateSubject.class, propertyFunc, propertyName);
    }

    @Override
    public DateSubject isEqualAccordingToCompareTo(Date other) {
        return null;
    }

    @Override
    public DateSubject isNotEqualAccordingToCompareTo(Date other) {
        return null;
    }

    @Override
    public DateSubject isLessThan(Date other) {
        return null;
    }

    @Override
    public DateSubject isLessThanOrEqualTo(Date other) {
        return null;
    }

    @Override
    public DateSubject isGreaterThan(Date other) {
        return null;
    }

    @Override
    public DateSubject isGreaterThanOrEqualTo(Date other) {
        return null;
    }

    @Override
    public DateSubject isBetween(Date startInclusive, Date endInclusive) {
        return null;
    }

    @Override
    public DateSubject isStrictlyBetween(Date startExclusive, Date endExclusive) {
        return null;
    }

    @Override
    public DateSubject isBetween(Date start, Date end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
