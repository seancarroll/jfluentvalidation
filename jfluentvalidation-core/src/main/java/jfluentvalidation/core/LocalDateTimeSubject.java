package jfluentvalidation.core;

import java.time.LocalDateTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class LocalDateTimeSubject
    extends Subject<LocalDateTimeSubject, LocalDateTime>
    implements ComparableSubject<LocalDateTimeSubject, LocalDateTime> {

    public LocalDateTimeSubject(Function<Object, LocalDateTime> propertyFunc, String propertyName) {
        super(LocalDateTime.class, propertyFunc, propertyName);
    }

    @Override
    public LocalDateTimeSubject isEqualAccordingToCompareTo(LocalDateTime other) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isNotEqualAccordingToCompareTo(LocalDateTime other) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isLessThan(LocalDateTime other) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isLessThanOrEqualTo(LocalDateTime other) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isGreaterThan(LocalDateTime other) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isGreaterThanOrEqualTo(LocalDateTime other) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isBetween(LocalDateTime startInclusive, LocalDateTime endInclusive) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isStrictlyBetween(LocalDateTime startExclusive, LocalDateTime endExclusive) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isBetween(LocalDateTime start, LocalDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isNotBetween(LocalDateTime startInclusive, LocalDateTime endInclusive) {
        return null;
    }

    @Override
    public LocalDateTimeSubject isNotBetween(LocalDateTime start, LocalDateTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
