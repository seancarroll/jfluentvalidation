package jfluentvalidation.common;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static jfluentvalidation.common.MoreCollections.safeContains;

// https://github.com/apache/commons-collections/blob/master/src/main/java/org/apache/commons/collections4/IterableUtils.java
public final class Iterables {

    private Iterables() {
        // statics only
    }

    public static int size(Iterable<?> iterable) {
        return iterable instanceof Collection
            ? ((Collection)iterable).size()
            : Iterators.size(iterable.iterator());
    }

    public static boolean contains(Iterable<?> iterable, @Nullable Object element) {
        if (iterable instanceof Collection) {
            Collection<?> collection = (Collection)iterable;
            return safeContains(collection, element);
        } else {
            return Iterators.contains(iterable.iterator(), element);
        }
    }

    public static boolean isEmpty(Iterable<?> iterable) {
        if (iterable instanceof Collection) {
            return ((Collection<?>) iterable).isEmpty();
        }
        return !iterable.iterator().hasNext();
    }

    public static <T> Collection<T> toCollection(Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return (Collection<T>) iterable;
        } else {
            List<T> collection = new ArrayList<>();
            iterable.forEach(collection::add);
            return collection;
        }
    }

}
