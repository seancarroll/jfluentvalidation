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
        super(OffsetTime.class, propertyFunc, propertyName);
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
        return null;
    }

    @Override
    public OffsetTimeSubject isNotEqualAccordingToCompareTo(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isLessThan(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isLessThanOrEqualTo(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isGreaterThan(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isGreaterThanOrEqualTo(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        return null;
    }

    @Override
    public OffsetTimeSubject isStrictlyBetween(OffsetTime startExclusive, OffsetTime endExclusive) {
        return null;
    }

    @Override
    public OffsetTimeSubject isBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public OffsetTimeSubject isNotBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        return null;
    }

    @Override
    public OffsetTimeSubject isNotBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
