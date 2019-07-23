package jfluentvalidation.common;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 */
public final class Iterators {

    private Iterators() {
        // statics only
    }

    /**
     *
     * @param iterator
     * @return
     */
    public static int size(final Iterator<?> iterator) {
        int size = 0;
        if (iterator != null) {
            while (iterator.hasNext()) {
                iterator.next();
                size++;
            }
        }
        return size;
    }

    /**
     *
     * @param iterator
     * @param element
     * @return
     */
    public static boolean contains(Iterator<?> iterator, @Nullable Object element) {
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), element)) {
                return true;
            }
        }
        return false;
    }
}
