package jfluentvalidation.core;

public interface ComparableSubject<S extends ComparableSubject<S, T>, T extends Comparable> {

    S isEqualAccordingToCompareTo(T other);

    S isNotEqualAccordingToCompareTo(T other);

    S isLessThan(T other);

    S isLessThanOrEqualTo(T other);

    S isGreaterThan(T other);

    S isGreaterThanOrEqualTo(T other);

    S isBetween(T startInclusive, T endInclusive);

    S isStrictlyBetween(T startExclusive, T endExclusive);

    S isBetween(T start, T end, boolean inclusiveStart, boolean inclusiveEnd);
}
