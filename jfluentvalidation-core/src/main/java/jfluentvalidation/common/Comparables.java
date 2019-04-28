package jfluentvalidation.common;

// https://github.com/SoftSmithy/softsmithy-lib/blob/2a46dd9566d22cd0fa038730ce8d2f63eb532b30/softsmithy-lib-core/src/main/java/org/softsmithy/lib/util/Comparables.java
// https://github.com/alexisjehan/javanilla/blob/929b8926277b4086ca9832cecaf8ecd2e6090505/src/main/java/com/github/alexisjehan/javanilla/lang/Comparables.java

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
     * @return
     */
    public static boolean isGreaterThan(Comparable actual, Comparable other) {
        return actual.compareTo(other) > 0;
    }

    /**
     *
     * @param actual
     * @param other
     * @return
     */
    public static boolean isLessThan(Comparable actual, Comparable other) {
        return actual.compareTo(other) < 0;
    }
}
