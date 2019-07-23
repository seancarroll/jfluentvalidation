package jfluentvalidation.common;

import java.util.List;

/**
 *
 * @param <T>
 */
public class IterableDifference<T> {

    private List<T> unexpected;
    private List<T> missing;

    /**
     *
     * @param actual
     * @param expected
     * @param <T>
     * @return
     */
    public static <T> IterableDifference<T> diff(Iterable<T> actual, Iterable<T> expected) {
        return new IterableDifference<>(actual, expected);
    }

    /**
     *
     * @param actual
     * @param expected
     */
    public IterableDifference(Iterable<T> actual, Iterable<T> expected) {
        this.unexpected = Iterables.subtract(actual, expected);
        this.missing = Iterables.subtract(expected, actual);
    }

    /**
     *
     * @return
     */
    public boolean differencesFound() {
        return hasUnexpected() || hasMissing();
    }

    /**
     *
     * @return  the elements in actual that are not in expected
     */
    public List<T> getUnexpected() {
        return unexpected;
    }

    public boolean hasUnexpected() {
        return !unexpected.isEmpty();
    }

    /**
     *
     * @return  the elements in actual that are not in expected
     */
    public List<T> getMissing() {
        return missing;
    }

    public boolean hasMissing() {
        return !missing.isEmpty();
    }
}
