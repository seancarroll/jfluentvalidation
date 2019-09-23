package jfluentvalidation.common;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.unmodifiableList;
import static jfluentvalidation.common.MoreCollections.safeContains;

// TODO: verify not a complete rip off of apache commons or guava
// https://github.com/apache/commons-collections/blob/master/src/main/java/org/apache/commons/collections4/IterableUtils.java

/**
 *
 */
public final class Iterables {

    private Iterables() {
        // statics only
    }

    /**
     * @param iterable
     * @return
     */
    public static int size(Iterable<?> iterable) {
        return iterable instanceof Collection
            ? ((Collection) iterable).size()
            : Iterators.size(iterable.iterator());
    }

    /**
     * @param iterable
     * @param element
     * @return
     */
    public static boolean contains(Iterable<?> iterable, @Nullable Object element) {
        if (iterable instanceof Collection) {
            Collection<?> collection = (Collection) iterable;
            return safeContains(collection, element);
        } else {
            return Iterators.contains(iterable.iterator(), element);
        }
    }

    /**
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

    public static <T> T[] toArray(Iterable<? extends T> iterable, T[] array) {
        return toCollection(iterable).toArray(array);
    }

    public static Object[] toArray(Iterable<?> iterable) {
        return toCollection(iterable).toArray();
    }

    /**
     * @param first
     * @param second
     * @param <T>
     * @return
     */
    public static <T> List<T> subtract(Iterable<T> first, Iterable<T> second) {
        List<T> missingInFirst = new ArrayList<>();
        // use a copy to deal correctly with potential duplicates
        List<T> copyOfSecond = Lists.newArrayList(second);
        for (T elementInFirst : first) {
            if (Iterables.contains(copyOfSecond, elementInFirst)) {
                // remove the element otherwise a duplicate would be found in the case if there is one in actual
                Iterables.removeFirst(copyOfSecond, elementInFirst);
            } else {
                missingInFirst.add(elementInFirst);
            }
        }
        return unmodifiableList(missingInFirst);
    }

    /**
     * @param iterable
     * @param value
     * @param <T>
     * @return
     */
    @CanIgnoreReturnValue
    public static <T> T removeFirst(Iterable<T> iterable, T value) {
        // TODO: should be throw instead?
        if (iterable == null) {
            return null;
        }
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (Objects.equals(next, value)) {
                iterator.remove();
                return next;
            }
        }
        return null;
    }

}
