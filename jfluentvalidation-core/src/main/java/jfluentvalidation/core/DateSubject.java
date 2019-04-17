package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.*;
import jfluentvalidation.rules.PropertyRule;

import java.util.Date;

// rather than isLessThan, isGreaterThan etc should we have isBefore, isAfter, etc? How to separate from other Comparable?
// NumberComparable vs TemporalComparable/some other name
/**
 *
 */
public class DateSubject extends Subject<DateSubject, Date> implements ComparableSubject<DateSubject, Date> {

    public DateSubject(PropertyRule<?, Date> rule) {
        super(DateSubject.class, rule);
    }

    public DateSubject isBefore(Date other) {
        rule.addConstraint(new IsLessThanConstraint(other));
        return myself;
    }

//    public DateSubject isBefore(String other) {
//        // TODO: parse string
//        return myself;
//    }

    public DateSubject isBeforeOrEqualTo(Date other) {
        // TODO: should this match or be closer to java8 Temporal classes
        rule.addConstraint(new IsLessThanOrEqualToConstraint(other));
        return myself;
    }

    public DateSubject isAfter(Date other) {
        rule.addConstraint(new IsGreaterThanConstraint(other));
        return myself;
    }

//    public DateSubject isAfter(String other) {
//        // TODO: parse string
//        return myself;
//    }

    public DateSubject isAfterOrEqualTo(Date other) {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint(other));
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
        return myself;
    }

    @Override
    public DateSubject isNotEqualAccordingToCompareTo(Date other) {
        return myself;
    }

    @Override
    public DateSubject isLessThan(Date other) {
        return myself;
    }

    @Override
    public DateSubject isLessThanOrEqualTo(Date other) {
        return myself;
    }

    @Override
    public DateSubject isGreaterThan(Date other) {
        return myself;
    }

    @Override
    public DateSubject isGreaterThanOrEqualTo(Date other) {
        return myself;
    }

    @Override
    public DateSubject isBetween(Date startInclusive, Date endInclusive) {
        return myself;
    }

    @Override
    public DateSubject isStrictlyBetween(Date startExclusive, Date endExclusive) {
        return myself;
    }

    @Override
    public DateSubject isBetween(Date start, Date end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    public DateSubject isNotBetween(Date start, Date end) {
        // TODO: should it be inclusive start and end?
        rule.addConstraint(new IsNotBetweenConstraint(start, end, true, true));
        return myself;
    }

    public DateSubject isNotBetween(Date start, Date end, boolean inclusiveStart, boolean inclusiveEnd) {
        // TODO: should it be inclusive start and end?
        rule.addConstraint(new IsNotBetweenConstraint(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
