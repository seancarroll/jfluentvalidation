package jfluentvalidation.core;

import jfluentvalidation.constraints.time.IsAfterOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsAfterZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeOrEqualZonedDateTimeConstraint;
import jfluentvalidation.constraints.time.IsBeforeZonedDateTimeConstraint;

import java.time.ZonedDateTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class ZonedDateTimeSubject
    extends Subject<ZonedDateTimeSubject, ZonedDateTime>
    implements ComparableSubject<ZonedDateTimeSubject, ZonedDateTime> {

    public ZonedDateTimeSubject(Function propertyFunc, String propertyName) {
        super(ZonedDateTimeSubject.class, propertyFunc, propertyName);
    }

    public ZonedDateTimeSubject isBefore(ZonedDateTime zonedDateTime) {
        constraints.add(new IsBeforeZonedDateTimeConstraint(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject isBeforeOrEqual(ZonedDateTime zonedDateTime) {
        constraints.add(new IsBeforeOrEqualZonedDateTimeConstraint(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject isAfter(ZonedDateTime zonedDateTime) {
        constraints.add(new IsAfterZonedDateTimeConstraint(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject isAfterOrEqual(ZonedDateTime zonedDateTime) {
        constraints.add(new IsAfterOrEqualZonedDateTimeConstraint(zonedDateTime));
        return  myself;
    }

    public ZonedDateTimeSubject isInTheFuture() {
        // TODO: clock from context/provider
        return isAfter(ZonedDateTime.now());
    }

    public ZonedDateTimeSubject isInTheFutureOrPresent() {
        // TODO: clock from context/provider
        return isAfterOrEqual(ZonedDateTime.now());
    }

    public ZonedDateTimeSubject isInThePast() {
        // TODO: clock from context/provider
        return isBefore(ZonedDateTime.now());
    }

    public ZonedDateTimeSubject isInThePastOrPresent() {
        // TODO: clock from context/provider
        return isBeforeOrEqual(ZonedDateTime.now());
    }

    @Override
    public ZonedDateTimeSubject isEqualAccordingToCompareTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isNotEqualAccordingToCompareTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isLessThan(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isLessThanOrEqualTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isGreaterThan(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isGreaterThanOrEqualTo(ZonedDateTime other) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isBetween(ZonedDateTime startInclusive, ZonedDateTime endInclusive) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isStrictlyBetween(ZonedDateTime startExclusive, ZonedDateTime endExclusive) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isBetween(ZonedDateTime start, ZonedDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isNotBetween(ZonedDateTime startInclusive, ZonedDateTime endInclusive) {
        return myself;
    }

    @Override
    public ZonedDateTimeSubject isNotBetween(ZonedDateTime start, ZonedDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return myself;
    }
}
