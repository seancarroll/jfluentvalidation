package jfluentvalidation.core;

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance
 * @param <A>  the type of the actual object being tested by this {@code Subject}
 */
public interface ComparableSubject<S extends ComparableSubject<S, T, A>, T, A extends Comparable<? super A>> {

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
