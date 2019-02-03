package jfluentvalidation.core;

import java.time.OffsetTime;
import java.util.function.Function;

// TODO: isEquals vs equals
public class OffsetTimeSubject
    extends Subject<OffsetTimeSubject, OffsetTime>
    implements ComparableSubject<OffsetTimeSubject, OffsetTime> {

    public OffsetTimeSubject(Function<Object, OffsetTime> propertyFunc, String propertyName) {
        super(OffsetTime.class, propertyFunc, propertyName);
    }

    @Override
    public OffsetTimeSubject isEqualAccordingToCompareTo(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isNotEqualAccordingToCompareTo(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isLessThan(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isLessThanOrEqualTo(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isGreaterThan(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isGreaterThanOrEqualTo(OffsetTime other) {
        return null;
    }

    @Override
    public OffsetTimeSubject isBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        return null;
    }

    @Override
    public OffsetTimeSubject isStrictlyBetween(OffsetTime startExclusive, OffsetTime endExclusive) {
        return null;
    }

    @Override
    public OffsetTimeSubject isBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public OffsetTimeSubject isNotBetween(OffsetTime startInclusive, OffsetTime endInclusive) {
        return null;
    }

    @Override
    public OffsetTimeSubject isNotBetween(OffsetTime start, OffsetTime end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
