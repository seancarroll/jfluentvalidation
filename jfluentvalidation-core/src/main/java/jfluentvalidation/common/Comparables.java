package jfluentvalidation.common;

// https://github.com/sunesimonsen/changeless/blob/7925d40590ec19f664294d96919c30e19ed9db37/src/main/java/com/jayway/changeless/utilities/Comparables.java
// https://github.com/alexisjehan/javanilla/blob/929b8926277b4086ca9832cecaf8ecd2e6090505/src/main/java/com/github/alexisjehan/javanilla/lang/Comparables.java

/**
 *
 */
public final class Comparables {

    private Comparables() {
        // statics only
    }

    /**
     * Returns true if actual is greater than other, false otherwise.
     *
     * @param actual  the actual value.
     * @param other  the value to compare the actual value to.
     * @param <T>  used to guarantee that two objects of the same type are being compared against each other.
     * @return  true if actual is greater than other, false otherwise.
     */
    public static <T extends Comparable<? super T>> boolean isGreaterThan(T actual, T other) {
        return actual.compareTo(other) > 0;
    }

    /**
     * Returns true if actual is greater than or equal to other, false otherwise.
     *
     * @param actual  the object to compare to other
     * @param other  the value to compare the actual value to. the object to compare to actual
     * @param <T>  the type of actual and expected
     * @return  true if actual is greater than or equal to other, false otherwise.
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
        return isBetween(actual, start, end, true,true);
    }

    /**
     *
     * @param actual  the actual value.
     * @param start  the start value.
     * @param end  the end value.
     * @param <T>
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isStrictlyBetween(T actual, T start, T end) {
        return isBetween(actual, start, end, false,false);
    }

    /**
     * the actual value is between start and end, inclusive or not.
     *
     * @param actual  the actual value.
     * @param start  the start value.
     * @param end  the end value.
     * @param inclusiveStart  if start is inclusive (fail is actual == start and inclusiveStart is false).
     * @param inclusiveEnd  if end is inclusive (fail is actual == end and inclusiveEnd is false).
     * @param <T>  used to guarantee that two objects of the same type are being compared against each other.     compared objects type.    The type of the values to compare.
     * @return
     */
    public static <T extends Comparable<? super T>> boolean isBetween(T actual, T start, T end, boolean inclusiveStart, boolean inclusiveEnd) {
        boolean checkLowerBoundaryRange = inclusiveStart ? !isGreaterThan(start, actual) : isLessThan(start, actual);
        boolean checkUpperBoundaryRange = inclusiveEnd ? !isGreaterThan(actual, end) : isLessThan(actual, end);

        return checkLowerBoundaryRange && checkUpperBoundaryRange;
    }

}
