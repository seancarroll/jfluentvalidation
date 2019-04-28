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
        // TODO: check if I can remove the null check and collapse this into a single check of Object.equals
//        if (element == null) {
//            while (iterator.hasNext()) {
//                if (iterator.next() == null) {
//                    return true;
//                }
//            }
//        } else {
//            while (iterator.hasNext()) {
//                if (element.equals(iterator.next())) {
//                    return true;
//                }
//            }
//        }
//        return false;

        // This seems to work and simpler than above...am I missing anything?
        while (iterator.hasNext()) {
            if (Objects.equals(iterator.next(), element)) {
                return true;
            }
        }
        return false;
    }
}
