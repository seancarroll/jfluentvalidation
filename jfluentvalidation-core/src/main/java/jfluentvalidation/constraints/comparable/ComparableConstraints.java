package jfluentvalidation.constraints.comparable;

// TODO: singleton instead of statics?

/**
 *
 */
public final class ComparableConstraints {

    // (both inclusive)
    public static <T, P extends Comparable<? super P>> IsBetweenConstraint<T, P> isBetween(P start, P end, boolean inclusiveStart, boolean inclusiveEnd) {
        return new IsBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd);
    }

    public static <T, P extends Comparable<? super P>> IsBetweenConstraint<T, P> isStrictlyBetween(P start, P end) {
        return new IsBetweenConstraint<>(start, end, false, false);
    }

    public static <T, P extends Comparable<? super P>> IsEqualAccordingToCompareToConstraint<T, P> isEqualAccordingToCompareTo(P other) {
        return new IsEqualAccordingToCompareToConstraint<>(other);
    }

    public static <T, P extends Comparable<? super P>> IsGreaterThanConstraint<T, P> isGreaterThan(P other) {
        return new IsGreaterThanConstraint<>(other);
    }

    public static <T, P extends Comparable<? super P>> IsGreaterThanOrEqualToConstraint<T, P> isGreaterThanOrEqualTo(P other) {
        return new IsGreaterThanOrEqualToConstraint<>(other);
    }

    public static <T, P extends Comparable<? super P>> IsLessThanConstraint<T, P> isLessThan(P other) {
        return new IsLessThanConstraint<>(other);
    }

    public static <T, P extends Comparable<? super P>> IsLessThanOrEqualToConstraint<T, P> isLessThanOrEqualTo(P other) {
        return new IsLessThanOrEqualToConstraint<>(other);
    }

    public static <T, P extends Comparable<? super P>> IsNotBetweenConstraint<T, P> isNotBetween(P start, P end, boolean inclusiveStart, boolean inclusiveEnd) {
        return new IsNotBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd);
    }

    public static <T, P extends Comparable<? super P>> IsNotEqualAccordingToCompareToConstraint<T, P> isNotEqualAccordingToCompareTo(P other) {
        return new IsNotEqualAccordingToCompareToConstraint<>(other);
    }

    private ComparableConstraints() {
        // public static factory methods only
    }
}
