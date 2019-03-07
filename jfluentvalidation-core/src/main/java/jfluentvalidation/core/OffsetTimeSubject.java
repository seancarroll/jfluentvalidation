package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetTimeConstraint;

import java.time.OffsetTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class OffsetTimeSubject
    extends Subject<OffsetTimeSubject, OffsetTime>
    implements ComparableSubject<OffsetTimeSubject, OffsetTime> {

    public OffsetTimeSubject(Function propertyFunc, String propertyName) {
        super(OffsetTimeSubject.class, propertyFunc, propertyName);
    }

    public OffsetTimeSubject isBefore(OffsetTime other) {
        constraints.add(new IsBeforeOffsetTimeConstraint(other));
        return  myself;
    }

    public OffsetTimeSubject isBeforeOrEqual(OffsetTime other) {
        constraints.add(new IsBeforeOrEqualOffsetTimeConstraint(other));
        return  myself;
    }

    public OffsetTimeSubject isAfter(OffsetTime other) {
        constraints.add(new IsAfterOffsetTimeConstraint(other));
        return  myself;
    }

    public OffsetTimeSubject isAfterOrEqual(OffsetTime other) {
        constraints.add(new IsAfterOrEqualOffsetTimeConstraint(other));
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
