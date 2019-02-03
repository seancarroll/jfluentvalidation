package jfluentvalidation.core;

import java.time.OffsetDateTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class OffsetDateTimeSubject
    extends Subject<OffsetDateTimeSubject, OffsetDateTime>
    implements ComparableSubject<OffsetDateTimeSubject, OffsetDateTime> {

    public OffsetDateTimeSubject(Function<Object, OffsetDateTime> propertyFunc, String propertyName) {
        super(OffsetDateTime.class, propertyFunc, propertyName);
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
