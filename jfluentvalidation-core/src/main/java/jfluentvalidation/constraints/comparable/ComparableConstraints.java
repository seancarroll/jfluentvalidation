package jfluentvalidation.constraints.comparable;

// TODO: should we use generics?
public final class ComparableConstraints {

    public static <T extends Comparable<? super T>> IsBetweenConstraint isBetween(Comparable<T> start, Comparable<T> end, boolean inclusiveStart, boolean inclusiveEnd) {
        return new IsBetweenConstraint(start, end, inclusiveStart, inclusiveEnd);
    }

    public static <T extends Comparable<? super T>> IsEqualAccordingToCompareToConstraint isEqualAccordingToCompareTo(Comparable<T> other) {
        return new IsEqualAccordingToCompareToConstraint(other);
    }

    public static <T extends Comparable<? super T>> IsGreaterThanConstraint isGreaterThan(Comparable<T> other) {
        return new IsGreaterThanConstraint(other);
    }

    public static <T extends Comparable<? super T>> IsGreaterThanOrEqualToConstraint isGreaterThanOrEqualTo(Comparable<T> other) {
        return new IsGreaterThanOrEqualToConstraint(other);
    }

    public static <T extends Comparable<? super T>> IsLessThanConstraint isLessThan(Comparable<T> other) {
        return new IsLessThanConstraint(other);
    }

    public static <T extends Comparable<? super T>> IsLessThanOrEqualToConstraint isLessThanOrEqualTo(Comparable<T> other) {
        return new IsLessThanOrEqualToConstraint(other);
    }

    public static <T extends Comparable<? super T>> IsNotBetweenConstraint isNotBetween(Comparable<T> start, Comparable<T> end, boolean inclusiveStart, boolean inclusiveEnd) {
        return new IsNotBetweenConstraint(start, end, inclusiveStart, inclusiveEnd);
    }

    public static <T extends Comparable<? super T>> IsNotEqualAccordingToCompareToConstraint isNotEqualAccordingToCompareTo(Comparable<T> other) {
        return new IsNotEqualAccordingToCompareToConstraint(other);
    }

    private ComparableConstraints() {
        // public static factory methods only
    }
}
