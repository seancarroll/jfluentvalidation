package jfluentvalidation.core;

import java.time.LocalDate;
import java.util.function.Function;

// TODO: isEquals vs equals
public class LocalDateSubject
    extends Subject<LocalDateSubject, LocalDate>
    implements ComparableSubject<LocalDateSubject, LocalDate> {

    public LocalDateSubject(Function<Object, LocalDate> propertyFunc, String propertyName) {
        super(LocalDate.class, propertyFunc, propertyName);
    }

    @Override
    public LocalDateSubject isEqualAccordingToCompareTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isNotEqualAccordingToCompareTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isLessThan(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isLessThanOrEqualTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isGreaterThan(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isGreaterThanOrEqualTo(LocalDate other) {
        return null;
    }

    @Override
    public LocalDateSubject isBetween(LocalDate startInclusive, LocalDate endInclusive) {
        return null;
    }

    @Override
    public LocalDateSubject isStrictlyBetween(LocalDate startExclusive, LocalDate endExclusive) {
        return null;
    }

    @Override
    public LocalDateSubject isBetween(LocalDate start, LocalDate end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public LocalDateSubject isNotBetween(LocalDate startInclusive, LocalDate endInclusive) {
        return null;
    }

    @Override
    public LocalDateSubject isNotBetween(LocalDate start, LocalDate end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }
}
