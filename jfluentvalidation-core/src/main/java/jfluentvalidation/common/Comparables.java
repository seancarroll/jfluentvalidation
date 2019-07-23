package jfluentvalidation.common;

/**
 *
 */
public final class Comparables {

    private Comparables() {
        // statics only
    }

    /**
     *
     * @param actual
     * @param other
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isGreaterThan(T actual, T other) {
        return actual.compareTo(other) > 0;
    }

    /**
     *
     * @param actual
     * @param other
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isGreaterThanOrEqual(T actual, T other) {
        return actual.compareTo(other) >= 0;
    }

    /**
     *
     * @param actual
     * @param other
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isLessThan(T actual, T other) {
        return actual.compareTo(other) < 0;
    }

    /**
     *
     * @param actual
     * @param other
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isLessThanOrEqual(T actual, T other) {
        return actual.compareTo(other) <= 0;
    }

    /**
     *
     * @param actual
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isBetween(T actual, T start, T end) {
        return isBetween(actual, start, end, true ,true);
    }

    /**
     *
     * @param actual
     * @param start
     * @param end
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isStrictlyBetween(T actual, T start, T end) {
        return isBetween(actual, start, end, false ,false);
    }

    /**
     *
     * @param actual
     * @param start
     * @param end
     * @param inclusiveStart
     * @param inclusiveEnd
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isBetween(T actual, T start, T end, boolean inclusiveStart, boolean inclusiveEnd) {
        boolean checkLowerBoundaryRange = inclusiveStart ? !isGreaterThan(start, actual) : isLessThan(start, actual);
        boolean checkUpperBoundaryRange = inclusiveEnd ? !isGreaterThan(actual, end) : isLessThan(actual, end);

        return checkLowerBoundaryRange && checkUpperBoundaryRange;
    }

}
