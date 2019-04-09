package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalDateTimeConstraint;

import java.time.LocalDateTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class LocalDateTimeSubject
    extends Subject<LocalDateTimeSubject, LocalDateTime>
    implements ComparableSubject<LocalDateTimeSubject, LocalDateTime> {

    public LocalDateTimeSubject(Function propertyFunc, String propertyName) {
        super(LocalDateTimeSubject.class, propertyFunc, propertyName);
    }

    public LocalDateTimeSubject isBefore(LocalDateTime other) {
        constraints.add(new IsBeforeLocalDateTimeConstraint(other));
        return  myself;
    }

    public LocalDateTimeSubject isBeforeOrEqual(LocalDateTime other) {
        constraints.add(new IsBeforeOrEqualLocalDateTimeConstraint(other));
        return  myself;
    }

    public LocalDateTimeSubject isAfter(LocalDateTime other) {
        constraints.add(new IsAfterLocalDateTimeConstraint(other));
        return  myself;
    }

    public LocalDateTimeSubject isAfterOrEqual(LocalDateTime other) {
        constraints.add(new IsAfterOrEqualLocalDateTimeConstraint(other));
        return  myself;
    }

    public LocalDateTimeSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalDateTime.now());
    }

    public LocalDateTimeSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(LocalDateTime.now());
    }

    public LocalDateTimeSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalDateTime.now());
    }

    public LocalDateTimeSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(LocalDateTime.now());
    }

    @Override
    public LocalDateTimeSubject isEqualAccordingToCompareTo(LocalDateTime other) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isNotEqualAccordingToCompareTo(LocalDateTime other) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isLessThan(LocalDateTime other) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isLessThanOrEqualTo(LocalDateTime other) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isGreaterThan(LocalDateTime other) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isGreaterThanOrEqualTo(LocalDateTime other) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isBetween(LocalDateTime startInclusive, LocalDateTime endInclusive) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isStrictlyBetween(LocalDateTime startExclusive, LocalDateTime endExclusive) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isBetween(LocalDateTime start, LocalDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isNotBetween(LocalDateTime startInclusive, LocalDateTime endInclusive) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject isNotBetween(LocalDateTime start, LocalDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
