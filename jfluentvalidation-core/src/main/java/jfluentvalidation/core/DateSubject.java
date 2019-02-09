package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.*;

import java.util.Date;
import java.util.function.Function;

// rather than isLessThan, isGreaterThan etc should we have isBefore, isAfter, etc? How to separate from other Comparable?
// NumberComparable vs TemporalComparable/some other name
public class DateSubject extends Subject<DateSubject, Date> implements ComparableSubject<DateSubject, Date> {

    public DateSubject(Function propertyFunc, String propertyName) {
        super(DateSubject.class, propertyFunc, propertyName);
    }

    public DateSubject isBefore(Date other) {
        
        constraints.add(new IsLessThanConstraint(other));
        return myself;
    }

//    public DateSubject isBefore(String other) {
//        // TODO: parse string
//        return myself;
//    }

    public DateSubject isBeforeOrEqualTo(Date other) {
        // TODO: should this match or be closer to java8 Temporal classes
        constraints.add(new IsLessThanOrEqualToConstraint(other));
        return myself;
    }

    public DateSubject isAfter(Date other) {
        constraints.add(new IsGreaterThanConstraint(other));
        return myself;
    }

//    public DateSubject isAfter(String other) {
//        // TODO: parse string
//        return myself;
//    }

    public DateSubject isAfterOrEqualTo(Date other) {
        constraints.add(new IsGreaterThanOrEqualToConstraint(other));
        return myself;
    }

    public DateSubject isInThePast() {
        // TODO: should we have a Clock?
        isBefore(new Date());
        return myself;
    }

    public DateSubject isToday() {
        // TODO:
        return myself;
    }

    public DateSubject isInTheFuture() {
        isAfter(new Date());
        return myself;
    }

    // TODO: isInSameYearAs
    // TODO: isInSameMonthAs
    // TOOD: isInSameDayAs

    // TODO: isEquals vs equals

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

    public DateSubject isNotBetween(Date start, Date end) {
        // TODO: should it be inclusive start and end?
        constraints.add(new IsNotBetweenConstraint(start, end, true, true));
        return myself;
    }

    public DateSubject isNotBetween(Date start, Date end, boolean inclusiveStart, boolean inclusiveEnd) {
        // TODO: should it be inclusive start and end?
        constraints.add(new IsNotBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
