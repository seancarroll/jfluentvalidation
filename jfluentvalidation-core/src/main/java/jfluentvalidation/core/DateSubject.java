package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.*;
import jfluentvalidation.rules.PropertyRule;

import java.util.Date;

// rather than isLessThan, isGreaterThan etc should we have isBefore, isAfter, etc? How to separate from other Comparable?
// NumberComparable vs TemporalComparable/some other name
/**
 *
 */
public class DateSubject<T> extends Subject<DateSubject<T>, T, Date> implements ComparableSubject<DateSubject<T>, T, Date> {

    public DateSubject(PropertyRule<T, Date> rule) {
        super(DateSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public DateSubject<T> isBefore(Date other) {
        rule.addConstraint(new IsLessThanConstraint<>(other));
        return myself;
    }

    public DateSubject<T> isBeforeOrEqualTo(Date other) {
        // TODO: should this match or be closer to java8 Temporal classes
        rule.addConstraint(new IsLessThanOrEqualToConstraint(other));
        return myself;
    }

    public DateSubject<T> isAfter(Date other) {
        rule.addConstraint(new IsGreaterThanConstraint(other));
        return myself;
    }

    public DateSubject<T> isAfterOrEqualTo(Date other) {
        rule.addConstraint(new IsGreaterThanOrEqualToConstraint(other));
        return myself;
    }

    public DateSubject<T> isInThePast() {
        // TODO: should we have a Clock?
        isBefore(new Date());
        return myself;
    }

    public DateSubject<T> isToday() {
        // TODO:
        return myself;
    }

    public DateSubject<T> isInTheFuture() {
        isAfter(new Date());
        return myself;
    }

    // TODO: isInSameYearAs
    // TODO: isInSameMonthAs
    // TOOD: isInSameDayAs

    // TODO: isEquals vs equals

    @Override
    public DateSubject<T> isEqualAccordingToCompareTo(Date other) {
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public DateSubject<T> isNotEqualAccordingToCompareTo(Date other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public DateSubject<T> isLessThan(Date other) {
        rule.addConstraint(ComparableConstraints.isLessThan(other));
        return myself;
    }

    @Override
    public DateSubject<T> isLessThanOrEqualTo(Date other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public DateSubject<T> isGreaterThan(Date other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public DateSubject<T> isGreaterThanOrEqualTo(Date other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
        return myself;
    }

    @Override
    public DateSubject<T> isBetween(Date startInclusive, Date endInclusive) {
        return isBetween(startInclusive, endInclusive, true, true);
    }

    @Override
    public DateSubject<T> isStrictlyBetween(Date startExclusive, Date endExclusive) {
        return myself;
    }

    @Override
    public DateSubject<T> isBetween(Date start, Date end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    public DateSubject<T> isNotBetween(Date start, Date end) {
        // TODO: should it be inclusive start and end?
        return isNotBetween(start, end, true, true);
    }

    public DateSubject<T> isNotBetween(Date start, Date end, boolean inclusiveStart, boolean inclusiveEnd) {
        // TODO: should it be inclusive start and end?
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
