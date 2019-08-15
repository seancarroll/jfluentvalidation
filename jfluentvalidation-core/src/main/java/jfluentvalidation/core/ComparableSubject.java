package jfluentvalidation.core;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance
 * @param <A>  the type of the actual object being tested by this {@code Subject}
 */
public interface ComparableSubject<S extends ComparableSubject<S, T, A>, T, A extends Comparable<? super A>> {

    @CanIgnoreReturnValue
    S isEqualAccordingToCompareTo(A other);

    @CanIgnoreReturnValue
    S isNotEqualAccordingToCompareTo(A other);

    @CanIgnoreReturnValue
    S isLessThan(A other);

    @CanIgnoreReturnValue
    S isLessThanOrEqualTo(A other);

    @CanIgnoreReturnValue
    S isGreaterThan(A other);

    @CanIgnoreReturnValue
    S isGreaterThanOrEqualTo(A other);

    @CanIgnoreReturnValue
    S isBetween(A startInclusive, A endInclusive);

    @CanIgnoreReturnValue
    S isStrictlyBetween(A startExclusive, A endExclusive);

    @CanIgnoreReturnValue
    S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd);

    @CanIgnoreReturnValue
    S isNotBetween(A startInclusive, A endInclusive);

    @CanIgnoreReturnValue
    S isNotBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd);
}
