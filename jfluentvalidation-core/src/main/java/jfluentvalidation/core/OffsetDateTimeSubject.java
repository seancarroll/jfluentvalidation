package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.OffsetDateTime;

// TODO: isEquals vs equals

/**
 *
 */
public class OffsetDateTimeSubject<T>
    extends Subject<OffsetDateTimeSubject<T>, T, OffsetDateTime>
    implements ComparableSubject<OffsetDateTimeSubject<T>, T, OffsetDateTime> {

    public OffsetDateTimeSubject(PropertyRule<T, OffsetDateTime> rule) {
        super(OffsetDateTimeSubject.class, rule);
    }

    public OffsetDateTimeSubject isBefore(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject isBeforeOrEqual(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject isAfter(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject isAfterOrEqual(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
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
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isNotEqualAccordingToCompareTo(OffsetDateTime other) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isLessThan(OffsetDateTime other) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isLessThanOrEqualTo(OffsetDateTime other) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isGreaterThan(OffsetDateTime other) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isGreaterThanOrEqualTo(OffsetDateTime other) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isBetween(OffsetDateTime startInclusive, OffsetDateTime endInclusive) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isStrictlyBetween(OffsetDateTime startExclusive, OffsetDateTime endExclusive) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isBetween(OffsetDateTime start, OffsetDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isNotBetween(OffsetDateTime startInclusive, OffsetDateTime endInclusive) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject isNotBetween(OffsetDateTime start, OffsetDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
