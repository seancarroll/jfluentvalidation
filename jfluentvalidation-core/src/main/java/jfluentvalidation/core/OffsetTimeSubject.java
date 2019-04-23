package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.OffsetTime;

// TODO: isEquals vs equals

/**
 *
 */
public class OffsetTimeSubject<T>
    extends Subject<OffsetTimeSubject<T>, T, OffsetTime>
    implements ComparableSubject<OffsetTimeSubject<T>, T, OffsetTime> {

    public OffsetTimeSubject(PropertyRule<T, OffsetTime> rule) {
        super(OffsetTimeSubject.class, rule);
    }

    public OffsetTimeSubject isBefore(OffsetTime other) {
        rule.addConstraint(new IsBeforeOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject isBeforeOrEqual(OffsetTime other) {
        rule.addConstraint(new IsBeforeOrEqualOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject isAfter(OffsetTime other) {
        rule.addConstraint(new IsAfterOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject isAfterOrEqual(OffsetTime other) {
        rule.addConstraint(new IsAfterOrEqualOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(OffsetTime.now());
    }

    public OffsetTimeSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(OffsetTime.now());
    }

    public OffsetTimeSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(OffsetTime.now());
    }

    public OffsetTimeSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(OffsetTime.now());
    }

    @Override
    public OffsetTimeSubject isEqualAccordingToCompareTo(OffsetTime other) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isNotEqualAccordingToCompareTo(OffsetTime other) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isLessThan(OffsetTime other) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isLessThanOrEqualTo(OffsetTime other) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isGreaterThan(OffsetTime other) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isGreaterThanOrEqualTo(OffsetTime other) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isStrictlyBetween(OffsetTime startExclusive, OffsetTime endExclusive) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isNotBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        return myself;
    }

    @Override
    public OffsetTimeSubject isNotBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
