package jfluentvalidation.core;

import jfluentvalidation.constraints.comparable.ComparableConstraints;
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

    // QUESTION: which do we want to keep? isBefore / isAfter vs past / future?
    public OffsetTimeSubject<T> isBefore(OffsetTime other) {
        rule.addConstraint(new IsBeforeOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject<T> isBeforeOrEqual(OffsetTime other) {
        rule.addConstraint(new IsBeforeOrEqualOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject<T> isAfter(OffsetTime other) {
        rule.addConstraint(new IsAfterOffsetTimeConstraint<>(other));
        return  myself;
    }

    public OffsetTimeSubject<T> isAfterOrEqual(OffsetTime other) {
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
    public OffsetTimeSubject<T> isEqualAccordingToCompareTo(OffsetTime other) {
        rule.addConstraint(ComparableConstraints.isEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public OffsetTimeSubject<T> isNotEqualAccordingToCompareTo(OffsetTime other) {
        rule.addConstraint(ComparableConstraints.isNotEqualAccordingToCompareTo(other));
        return myself;
    }

    @Override
    public OffsetTimeSubject<T> isLessThan(OffsetTime other) {
        rule.addConstraint(ComparableConstraints.isLessThan(other));
        return myself;
    }

    @Override
    public OffsetTimeSubject<T> isLessThanOrEqualTo(OffsetTime other) {
        rule.addConstraint(ComparableConstraints.isLessThanOrEqualTo(other));
        return myself;
    }

    @Override
    public OffsetTimeSubject<T> isGreaterThan(OffsetTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThan(other));
        return myself;
    }

    @Override
    public OffsetTimeSubject<T> isGreaterThanOrEqualTo(OffsetTime other) {
        rule.addConstraint(ComparableConstraints.isGreaterThanOrEqualTo(other));
        return myself;
    }

    // TODO: do we want string versions?

    @Override
    public OffsetTimeSubject<T> isBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        return isBetween(startInclusive, endInclusive, true, true);
    }

    @Override
    public OffsetTimeSubject<T> isStrictlyBetween(OffsetTime startExclusive, OffsetTime endExclusive) {
        return isBetween(startExclusive, endExclusive, false, false);
    }

    @Override
    public OffsetTimeSubject<T> isBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }

    @Override
    public OffsetTimeSubject<T> isNotBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        // QUESTION: hmmm...should whould the start and end be inclusive be consistent between isBetween and isNotBetween?
        rule.addConstraint(ComparableConstraints.isNotBetween(startInclusive, endInclusive, true, false));
        return myself;
    }

    @Override
    public OffsetTimeSubject<T> isNotBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        rule.addConstraint(ComparableConstraints.isNotBetween(start, end, inclusiveStart, inclusiveEnd));
        return myself;
    }
}
