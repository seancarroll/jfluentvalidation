package jfluentvalidation.core;

/**
 *
 * @param <S>
 * @param <A>
 */
public interface ComparableSubject<S extends ComparableSubject<S, A>, A extends Comparable<? super A>> {

    S isEqualAccordingToCompareTo(A other);

    S isNotEqualAccordingToCompareTo(A other);

    S isLessThan(A other);

    S isLessThanOrEqualTo(A other);

    S isGreaterThan(A other);

    S isGreaterThanOrEqualTo(A other);

    // TODO: review. Do most things use inclusive start and an exclusive end? Duration.between does. others?
    // Duration.between()
    // Period.between()
    // ChronoUnit between
    S isBetween(A startInclusive, A endInclusive);

    S isStrictlyBetween(A startExclusive, A endExclusive);

    S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd);

    S isNotBetween(A startInclusive, A endInclusive);

    S isNotBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd);
}
