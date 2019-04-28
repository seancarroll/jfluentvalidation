package jfluentvalidation.common;

import jfluentvalidation.internal.Ensure;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public final class MoreCollections {

    private MoreCollections() {
        // statics only
    }

    /**
     *
     * @param collection
     * @param object
     * @return
     */
    public static boolean safeContains(Collection<?> collection, @Nullable Object object) {
        Ensure.notNull(collection);
        try {
            return collection.contains(object);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    // gather / collect
    /**
     *
     * @param first
     * @param second
     * @param rest
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> List<T> accumulate(T first, T second, T... rest) {
        List<T> items = new ArrayList<>(2 + (rest == null ? 1 : rest.length));
        items.add(first);
        items.add(second);
        // rest is an empty array when the caller provides only two parameters which means that if rest is null
        // then the caller must have passed in null
        if (rest == null) {
            items.add(null);
        } else {
            items.addAll(Arrays.asList(rest));
        }
        return items;
    }

//    from assertj
//    public static <T> T[] prepend(T first, T... rest) {
//        T[] result = (T[]) new Object[1 + rest.length];
//        result[0] = first;
//        System.arraycopy(rest, 0, result, 1, rest.length);
//        return result;
//    }
}
