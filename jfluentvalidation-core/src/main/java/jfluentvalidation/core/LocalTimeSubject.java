package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalTime;

// TODO: isEquals vs equals

/**
 *
 */
public class LocalTimeSubject<T>
    extends Subject<LocalTimeSubject<T>, T, LocalTime>
    implements ComparableSubject<LocalTimeSubject<T>, T, LocalTime> {

    public LocalTimeSubject(PropertyRule<T, LocalTime> rule) {
        super(LocalTimeSubject.class, rule);
    }

    // before and after use compareTo...do we want to keep both?
    public LocalTimeSubject isBefore(LocalTime other) {
        rule.addConstraint(new IsBeforeLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject isBeforeOrEqual(LocalTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject isAfter(LocalTime other) {
        rule.addConstraint(new IsAfterLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject isAfterOrEqual(LocalTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalTimeConstraint<>(other));
        return  myself;
    }

    public LocalTimeSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalTime.now());
    }

    public LocalTimeSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(LocalTime.now());
    }

    public LocalTimeSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalTime.now());
    }

    public LocalTimeSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(LocalTime.now());
    }

    @Override
    public LocalTimeSubject isEqualAccordingToCompareTo(LocalTime other) {
        return myself;
    }

    @Override
    public LocalTimeSubject isNotEqualAccordingToCompareTo(LocalTime other) {
        return myself;
    }

    @Override
    public LocalTimeSubject isLessThan(LocalTime other) {
        return myself;
    }

    @Override
    public LocalTimeSubject isLessThanOrEqualTo(LocalTime other) {
        return myself;
    }

    @Override
    public LocalTimeSubject isGreaterThan(LocalTime other) {
        return myself;
    }

    @Override
    public LocalTimeSubject isGreaterThanOrEqualTo(LocalTime other) {
        return myself;
    }

    @Override
    public LocalTimeSubject isBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return myself;
    }

    @Override
    public LocalTimeSubject isStrictlyBetween(LocalTime startExclusive, LocalTime endExclusive) {
        return myself;
    }

    @Override
    public LocalTimeSubject isBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public LocalTimeSubject isNotBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return myself;
    }

    @Override
    public LocalTimeSubject isNotBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
