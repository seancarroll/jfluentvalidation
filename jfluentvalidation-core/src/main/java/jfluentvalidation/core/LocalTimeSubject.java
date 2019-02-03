package jfluentvalidation.core;

import java.time.LocalTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class LocalTimeSubject
    extends Subject<LocalTimeSubject, LocalTime>
    implements ComparableSubject<LocalTimeSubject, LocalTime> {

    public LocalTimeSubject(Function<Object, LocalTime> propertyFunc, String propertyName) {
        super(LocalTime.class, propertyFunc, propertyName);
    }

    @Override
    public LocalTimeSubject isEqualAccordingToCompareTo(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isNotEqualAccordingToCompareTo(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isLessThan(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isLessThanOrEqualTo(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isGreaterThan(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isGreaterThanOrEqualTo(LocalTime other) {
        return null;
    }

    @Override
    public LocalTimeSubject isBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return null;
    }

    @Override
    public LocalTimeSubject isStrictlyBetween(LocalTime startExclusive, LocalTime endExclusive) {
        return null;
    }

    @Override
    public LocalTimeSubject isBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public LocalTimeSubject isNotBetween(LocalTime startInclusive, LocalTime endInclusive) {
        return null;
    }

    @Override
    public LocalTimeSubject isNotBetween(LocalTime start, LocalTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
