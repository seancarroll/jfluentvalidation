package jfluentvalidation.core;

public interface ComparableSubject<S extends ComparableSubject<S, A>, A extends Comparable> {

    S isEqualAccordingToCompareTo(A other);

    S isNotEqualAccordingToCompareTo(A other);

    S isLessThan(A other);

    S isLessThanOrEqualTo(A other);

    S isGreaterThan(A other);

    S isGreaterThanOrEqualTo(A other);

    S isBetween(A startInclusive, A endInclusive);

    S isStrictlyBetween(A startExclusive, A endExclusive);

    S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd);

    S isNotBetween(A startInclusive, A endInclusive);

    S isNotBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd);
}
