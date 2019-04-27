package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.ComparableConstraints;
import jfluentvalidation.constraints.time.IsAfterLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterOrEqualLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeLocalDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualLocalDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.LocalDateTime;

// TODO: isEquals vs equals

/**
 *
 */
public class LocalDateTimeSubject<T>
    extends Subject<LocalDateTimeSubject<T>, T, LocalDateTime>
    implements ComparableSubject<LocalDateTimeSubject<T>, T, LocalDateTime> {

    public LocalDateTimeSubject(PropertyRule<T, LocalDateTime> rule) {
        super(LocalDateTimeSubject.class, rule);
    }

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public LocalDateTimeSubject isBefore(LocalDateTime other) {
        rule.addConstraint(new IsBeforeLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isBeforeOrEqual(LocalDateTime other) {
        rule.addConstraint(new IsBeforeOrEqualLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isAfter(LocalDateTime other) {
        rule.addConstraint(new IsAfterLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isAfterOrEqual(LocalDateTime other) {
        rule.addConstraint(new IsAfterOrEqualLocalDateTimeConstraint<>(other));
        return  myself;
    }

    public LocalDateTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(LocalDateTime.now());
    }

    public LocalDateTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(LocalDateTime.now());
    }

    public LocalDateTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(LocalDateTime.now());
    }

    public LocalDateTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(LocalDateTime.now());
    }

    @Override
    public LocalDateTimeSubject<T> isEqualAccordingToCompareTo(LocalDateTime other) {
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isNotEqualAccordingToCompareTo(LocalDateTime other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isLessThan(LocalDateTime other) {
        ComparableConstraints.isLessThan(other);
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isLessThanOrEqualTo(LocalDateTime other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isGreaterThan(LocalDateTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isGreaterThanOrEqualTo(LocalDateTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isBetween(LocalDateTime startInclusive, LocalDateTime endInclusive) {
        return isBetween(startInclusive, endInclusive, true, true);
    }

    @Override
    public LocalDateTimeSubject<T> isStrictlyBetween(LocalDateTime startExclusive, LocalDateTime endExclusive) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isBetween(LocalDateTime start, LocalDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isNotBetween(LocalDateTime startInclusive, LocalDateTime endInclusive) {
        return myself;
    }

    @Override
    public LocalDateTimeSubject<T> isNotBetween(LocalDateTime start, LocalDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
