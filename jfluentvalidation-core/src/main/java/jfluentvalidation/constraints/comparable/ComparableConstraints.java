package jfluentvalidation.constraints.comparable;

/**
 *
 */
public final class ComparableConstraints {

    public static <T extends Comparable<? super T>> IsBetweenConstraint isBetween(T start, T end, boolean inclusiveStart, boolean inclusiveEnd) {
        return new IsBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd);
    }

    public static <T extends Comparable<? super T>> IsEqualAccordingToCompareToConstraint isEqualAccordingToCompareTo(T other) {
        return new IsEqualAccordingToCompareToConstraint<>(other);
    }

    public static <T extends Comparable<? super T>> IsGreaterThanConstraint isGreaterThan(T other) {
        return new IsGreaterThanConstraint<>(other);
    }

    public static <T extends Comparable<? super T>> IsGreaterThanOrEqualToConstraint isGreaterThanOrEqualTo(T other) {
        return new IsGreaterThanOrEqualToConstraint<>(other);
    }

    public static <T extends Comparable<? super T>> IsLessThanConstraint isLessThan(T other) {
        return new IsLessThanConstraint<>(other);
    }

    public static <T extends Comparable<? super T>> IsLessThanOrEqualToConstraint isLessThanOrEqualTo(T other) {
        return new IsLessThanOrEqualToConstraint<>(other);
    }

    public static <T extends Comparable<? super T>> IsNotBetweenConstraint isNotBetween(T start, T end, boolean inclusiveStart, boolean inclusiveEnd) {
        return new IsNotBetweenConstraint<>(start, end, inclusiveStart, inclusiveEnd);
    }

    public static <T extends Comparable<? super T>> IsNotEqualAccordingToCompareToConstraint isNotEqualAccordingToCompareTo(T other) {
        return new IsNotEqualAccordingToCompareToConstraint<>(other);
    }

    private ComparableConstraints() {
        // public static factory methods only
    }
}
