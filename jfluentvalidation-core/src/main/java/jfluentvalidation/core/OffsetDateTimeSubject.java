package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.ComparableConstraints;
import jfluentvalidation.constraints.time.IsAfterOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOffsetDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualOffsetDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.OffsetDateTime;

// TODO: isEquals vs equals

/**
 *
 * @param <T>  the type of the instance
 */
public class OffsetDateTimeSubject<T>
    extends Subject<OffsetDateTimeSubject<T>, T, OffsetDateTime>
    implements ComparableSubject<OffsetDateTimeSubject<T>, T, OffsetDateTime> {

    public OffsetDateTimeSubject(PropertyRule<T, OffsetDateTime> rule) {
        super(OffsetDateTimeSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public OffsetDateTimeSubject<T> isBefore(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject<T> isBeforeOrEqual(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsBeforeOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject<T> isAfter(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject<T> isAfterOrEqual(OffsetDateTime offsetDateTime) {
        rule.addConstraint(new IsAfterOrEqualOffsetDateTimeConstraint<>(offsetDateTime));
        return  myself;
    }

    public OffsetDateTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(OffsetDateTime.now());
    }

    public OffsetDateTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(OffsetDateTime.now());
    }

    public OffsetDateTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(OffsetDateTime.now());
    }

    public OffsetDateTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(OffsetDateTime.now());
    }

    @Override
    public OffsetDateTimeSubject<T> isEqualAccordingToCompareTo(OffsetDateTime other) {
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isNotEqualAccordingToCompareTo(OffsetDateTime other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isLessThan(OffsetDateTime other) {
        rule.addConstraint(ComparableConstraints.isLessThan(other));
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isLessThanOrEqualTo(OffsetDateTime other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isGreaterThan(OffsetDateTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isGreaterThanOrEqualTo(OffsetDateTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isBetween(OffsetDateTime startInclusive, OffsetDateTime endInclusive) {
        return isBetween(startInclusive, endInclusive, true, true);
    }

    @Override
    public OffsetDateTimeSubject<T> isStrictlyBetween(OffsetDateTime startExclusive, OffsetDateTime endExclusive) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isBetween(OffsetDateTime start, OffsetDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isNotBetween(OffsetDateTime startInclusive, OffsetDateTime endInclusive) {
        return myself;
    }

    @Override
    public OffsetDateTimeSubject<T> isNotBetween(OffsetDateTime start, OffsetDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
