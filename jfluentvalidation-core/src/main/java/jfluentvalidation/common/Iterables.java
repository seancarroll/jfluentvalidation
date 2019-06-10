package jfluentvalidation.common;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jfluentvalidation.common.MoreCollections.safeContains;

// https://github.com/apache/commons-collections/blob/master/src/main/java/org/apache/commons/collections4/IterableUtils.java

/**
 *
 */
public final class Iterables {

    private Iterables() {
        // statics only
    }

    /**
     *
     * @param iterable
     * @return
     */
    public static int size(Iterable<?> iterable) {
        return iterable instanceof Collection
            ? ((Collection)iterable).size()
            : Iterators.size(iterable.iterator());
    }

    /**
     *
     * @param iterable
     * @param element
     * @return
     */
    public static boolean contains(Iterable<?> iterable, @Nullable Object element) {
        if (iterable instanceof Collection) {
            Collection<?> collection = (Collection)iterable;
            return safeContains(collection, element);
        } else {
            return Iterators.contains(iterable.iterator(), element);
        }
    }

    /**
     *
     * @param iterable
     * @return
     */
    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    /**
     *
     * @param iterable
     * @param <T>
     * @return
     */
    public static <T> Collection<T> toCollection(Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return (Collection<T>) iterable;
        } else {
            List<T> collection = new ArrayList<>();
            iterable.forEach(collection::add);
            return collection;
        }
    }

    public static <T> T[] toArray(Iterable<? extends T> iterable, Class<T> type) {
        return toArray(iterable, MoreArrays.newArray(type, 0));
    }

    static <T> T[] toArray(Iterable<? extends T> iterable, T[] array) {
        return toCollection(iterable).toArray(array);
    }

    public static Object[] toArray(Iterable<?> iterable) {
        return toCollection(iterable).toArray();
    }

// this toCollection is from java.util.stream
//    public static <T> ArrayList<T> newArrayList(Iterable<? extends T> elements) {
//        if (elements == null) {
//            return null;
//        }
//        return Streams.stream(elements).collect(toCollection(ArrayList::new));
//    }

}
