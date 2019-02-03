package jfluentvalidation.core;

import java.time.ZonedDateTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class ZonedDateTimeSubject
    extends Subject<ZonedDateTimeSubject, ZonedDateTime>
    implements ComparableSubject<ZonedDateTimeSubject, ZonedDateTime> {

    public ZonedDateTimeSubject(Function<Object, ZonedDateTime> propertyFunc, String propertyName) {
        super(ZonedDateTimeSubject.class, propertyFunc, propertyName);
    }

    @Override
    public ZonedDateTimeSubject isEqualAccordingToCompareTo(ZonedDateTime other) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isNotEqualAccordingToCompareTo(ZonedDateTime other) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isLessThan(ZonedDateTime other) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isLessThanOrEqualTo(ZonedDateTime other) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isGreaterThan(ZonedDateTime other) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isGreaterThanOrEqualTo(ZonedDateTime other) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isBetween(ZonedDateTime startInclusive, ZonedDateTime endInclusive) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isStrictlyBetween(ZonedDateTime startExclusive, ZonedDateTime endExclusive) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isBetween(ZonedDateTime start, ZonedDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isNotBetween(ZonedDateTime startInclusive, ZonedDateTime endInclusive) {
        return null;
    }

    @Override
    public ZonedDateTimeSubject isNotBetween(ZonedDateTime start, ZonedDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
