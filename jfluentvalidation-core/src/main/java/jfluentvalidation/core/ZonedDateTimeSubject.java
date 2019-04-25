package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeZonedDateTimeConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.time.ZonedDateTime;

// TODO: isEquals vs equals

/**
 *
 */
public class ZonedDateTimeSubject<T>
    extends Subject<ZonedDateTimeSubject<T>, T, ZonedDateTime>
    implements ComparableSubject<ZonedDateTimeSubject<T>, T, ZonedDateTime> {


    public ZonedDateTimeSubject(PropertyRule<T, ZonedDateTime> rule) {
        super(ZonedDateTimeSubject.class, rule);
    }

    public ZonedDateTimeSubject<T> isBefore(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsBeforeZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject<T> isBeforeOrEqual(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsBeforeOrEqualZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject<T> isAfter(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsAfterZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject<T> isAfterOrEqual(ZonedDateTime zonedDateTime) {
        rule.addConstraint(new IsAfterOrEqualZonedDateTimeConstraint<>(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject<T> isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(ZonedDateTime.now());
    }

    public ZonedDateTimeSubject<T> isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(ZonedDateTime.now());
    }

    public ZonedDateTimeSubject<T> isInThePast() {
        // TODO: clock from context/provider
        return isBefore(ZonedDateTime.now());
    }

    public ZonedDateTimeSubject<T> isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(ZonedDateTime.now());
    }

    @Override
    public ZonedDateTimeSubject<T> isEqualAccordingToCompareTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isNotEqualAccordingToCompareTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isLessThan(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isLessThanOrEqualTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isGreaterThan(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isGreaterThanOrEqualTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isBetween(ZonedDateTime startInclusive, ZonedDateTime endInclusive) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isStrictlyBetween(ZonedDateTime startExclusive, ZonedDateTime endExclusive) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isBetween(ZonedDateTime start, ZonedDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isNotBetween(ZonedDateTime startInclusive, ZonedDateTime endInclusive) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject<T> isNotBetween(ZonedDateTime start, ZonedDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
