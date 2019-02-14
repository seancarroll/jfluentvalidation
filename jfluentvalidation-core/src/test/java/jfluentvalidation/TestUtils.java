package jfluentvalidation;

import java.util.Arrays;
import java.util.Iterator;

// TODO: something better than this?
public final class TestUtils {

    private TestUtils() {
        // statics only
    }

    public static <T> Iterable<T> createIterable(final T... values) {
        final Iterator<T> iterator = Arrays.asList(values).listIterator();
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return iterator;
            }

            @Override
            public String toString() {
                return Arrays.toString(values);
            }
        };
    }
}
