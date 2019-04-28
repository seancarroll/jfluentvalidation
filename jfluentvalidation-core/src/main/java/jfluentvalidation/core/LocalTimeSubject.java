package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.ComparableConstraints;
import jfluentvalidation.constraints.time.IsAfterLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalTime;

// TODO: isEquals vs equals

/**
 *
 * @param <T>  the type of the instance
 */
public class LocalTimeSubject<T>
    extends Subject<LocalTimeSubject<T>, T, LocalTime>
    implements ComparableSubject<LocalTimeSubject<T>, T, LocalTime> {

    public LocalTimeSubject(PropertyRule<T, LocalTime> rule) {
        super(LocalTimeSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    // before and after use compareTo...do we want to keep both?
    public LocalTimeSubject<T> isBefore(LocalTime other) {
        rule.addConstraint(new IsBeforeLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isBeforeOrEqual(LocalTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isAfter(LocalTime other) {
        rule.addConstraint(new IsAfterLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isAfterOrEqual(LocalTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalTime.now());
    }

    public LocalTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(LocalTime.now());
    }

    public LocalTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalTime.now());
    }

    public LocalTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(LocalTime.now());
    }

    @Override
    public LocalTimeSubject<T> isEqualAccordingToCompareTo(LocalTime other) {
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isNotEqualAccordingToCompareTo(LocalTime other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isLessThan(LocalTime other) {
        rule.addConstraint(ComparableConstraints.isLessThan(other));
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isLessThanOrEqualTo(LocalTime other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isGreaterThan(LocalTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isGreaterThanOrEqualTo(LocalTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return isBetween(startInclusive, endInclusive, true, true);
    }

    @Override
    public LocalTimeSubject<T> isStrictlyBetween(LocalTime startExclusive, LocalTime endExclusive) {
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isNotBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return myself;
    }

    @Override
    public LocalTimeSubject<T> isNotBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
