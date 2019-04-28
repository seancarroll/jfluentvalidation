package jfluentvalidation.common;

public final class Comparables {

    public static boolean isGreaterThan(Comparable actual, Comparable other) {
        return actual.compareTo(other) > 0;
    }

    public static boolean isLessThan(Comparable actual, Comparable other) {
        return actual.compareTo(other) < 0;
    }

    private Comparables() {
        // public static methods only
    }
}
