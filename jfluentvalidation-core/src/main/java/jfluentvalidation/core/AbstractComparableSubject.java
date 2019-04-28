package jfluentvalidation.core;

/**
 *
 * @param <S>  the self-type, allowing {@code this}-returning methods to avoid needing subclassing.
 *             Additional details can be found at &quot;<a href="http://bit.ly/1IZIRcY" target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 * @param <T>  the type of the instance.
 * @param <A>  the type of the actual object being tested by this {@code Subject}.
 */
public class AbstractComparableSubject<S extends AbstractComparableSubject<S, T, A>, T, A extends Comparable<? super A>>
    implements ComparableSubject<S, T, A> {

    @Override
    public S isEqualAccordingToCompareTo(A other) {
        return null;
    }

    @Override
    public S isNotEqualAccordingToCompareTo(A other) {
        return null;
    }

    @Override
    public S isLessThan(A other) {
        return null;
    }

    @Override
    public S isLessThanOrEqualTo(A other) {
        return null;
    }

    @Override
    public S isGreaterThan(A other) {
        return null;
    }

    @Override
    public S isGreaterThanOrEqualTo(A other) {
        return null;
    }

    @Override
    public S isBetween(A startInclusive, A endInclusive) {
        return null;
    }

    @Override
    public S isStrictlyBetween(A startExclusive, A endExclusive) {
        return null;
    }

    @Override
    public S isBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    @Override
    public S isNotBetween(A startInclusive, A endInclusive) {
        return null;
    }

    @Override
    public S isNotBetween(A start, A end, boolean inclusiveStart, boolean inclusiveEnd) {
        return null;
    }

    //    @Override
//    public S isEqualAccordingToCompareTo(A other) {
//        return null;
//    }
//
//    @Override
//    public S isNotEqualAccordingToCompareTo(A other) {
//        return null;
//    }
//
//    @Override
//    public S isLessThan(A other) {
//        return null;
//    }
//
//    @Override
//    public S isLessThanOrEqualTo(A other) {
//        return null;
//    }
//
//    @Override
//    public S isGreaterThan(A other) {
//        return null;
//    }
//
//    @Override
//    public S isGreaterThanOrEqualTo(A other) {
//        return null;
//    }
//
//    @Override
//    public S isBetween(A startInclusive, A endInclusive) {
//        return null;
//    }
//
//    @Override
//    public S isStrictlyBetween(A startExclusive, A endExclusive) {
//        return null;
//    }
}
