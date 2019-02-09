package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalTimeConstraint;

import java.time.LocalTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class LocalTimeSubject
    extends Subject<LocalTimeSubject, LocalTime>
    implements ComparableSubject<LocalTimeSubject, LocalTime> {

    public LocalTimeSubject(Function propertyFunc, String propertyName) {
        super(LocalTime.class, propertyFunc, propertyName);
    }

    // before and after use compareTo...do we want to keep both?
    public LocalTimeSubject isBefore(LocalTime other) {
        constraints.add(new IsBeforeLocalTimeConstraint(other));
        return  myself;
    }

    public LocalTimeSubject isBeforeOrEqual(LocalTime other) {
        constraints.add(new IsBeforeOrEqualLocalTimeConstraint(other));
        return  myself;
    }

    public LocalTimeSubject isAfter(LocalTime other) {
        constraints.add(new IsAfterLocalTimeConstraint(other));
        return  myself;
    }

    public LocalTimeSubject isAfterOrEqual(LocalTime other) {
        constraints.add(new IsAfterOrEqualLocalTimeConstraint(other));
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
        return null;
    }

    @Override
    public LocalTimeSubject isNotEqualAccordingToCompareTo(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isLessThan(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isLessThanOrEqualTo(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isGreaterThan(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isGreaterThanOrEqualTo(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return null;
    }

    @Override
    public LocalTimeSubject isStrictlyBetween(LocalTime startExclusive, LocalTime endExclusive) {
        return null;
    }

    @Override
    public LocalTimeSubject isBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public LocalTimeSubject isNotBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return null;
    }

    @Override
    public LocalTimeSubject isNotBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
