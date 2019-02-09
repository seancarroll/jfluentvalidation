package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetDateTimeConstraint;

import java.time.OffsetDateTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class OffsetDateTimeSubject
    extends Subject<OffsetDateTimeSubject, OffsetDateTime>
    implements ComparableSubject<OffsetDateTimeSubject, OffsetDateTime> {

    public OffsetDateTimeSubject(Function propertyFunc, String propertyName) {
        super(OffsetDateTime.class, propertyFunc, propertyName);
    }

    public OffsetDateTimeSubject isBefore(OffsetDateTime offsetDateTime) {
        constraints.add(new IsBeforeOffsetDateTimeConstraint(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject isBeforeOrEqual(OffsetDateTime offsetDateTime) {
        constraints.add(new IsBeforeOrEqualOffsetDateTimeConstraint(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject isAfter(OffsetDateTime offsetDateTime) {
        constraints.add(new IsAfterOffsetDateTimeConstraint(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject isAfterOrEqual(OffsetDateTime offsetDateTime) {
        constraints.add(new IsAfterOrEqualOffsetDateTimeConstraint(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(OffsetDateTime.now());
    }

    public OffsetDateTimeSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(OffsetDateTime.now());
    }

    public OffsetDateTimeSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(OffsetDateTime.now());
    }

    public OffsetDateTimeSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(OffsetDateTime.now());
    }

    @Override
    public OffsetDateTimeSubject isEqualAccordingToCompareTo(OffsetDateTime other) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isNotEqualAccordingToCompareTo(OffsetDateTime other) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isLessThan(OffsetDateTime other) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isLessThanOrEqualTo(OffsetDateTime other) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isGreaterThan(OffsetDateTime other) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isGreaterThanOrEqualTo(OffsetDateTime other) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isBetween(OffsetDateTime startInclusive, OffsetDateTime endInclusive) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isStrictlyBetween(OffsetDateTime startExclusive, OffsetDateTime endExclusive) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isBetween(OffsetDateTime start, OffsetDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isNotBetween(OffsetDateTime startInclusive, OffsetDateTime endInclusive) {
        return null;
    }

    @Override
    public OffsetDateTimeSubject isNotBetween(OffsetDateTime start, OffsetDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
