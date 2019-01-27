package jfluentvalidation.core;

public class AbstractComparableSubject<S extends AbstractComparableSubject<S, A>, A extends Comparable<? super A>>
    implements ComparableSubject<S, A> {

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
