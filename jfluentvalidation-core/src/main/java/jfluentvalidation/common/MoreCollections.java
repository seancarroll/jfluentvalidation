package jfluentvalidation.common;

import jfluentvalidation.internal.Ensure;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collection;

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

    /** Used to avoid http://bugs.sun.com/view_bug.do?bug_id=6558557 */
    static <T> Collection<T> cast(Iterable<T> iterable) {
        return (Collection<T>) iterable;
    }
}
