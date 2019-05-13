package jfluentvalidation.common;

import java.lang.reflect.Array;

public final class Arrays {

    private Arrays() {
        // statics only
    }

    public static int size(Object array) {
        if (array instanceof Object[]) return ((Object[]) array).length;
        return Array.getLength(array);
    }

    /**
     * Indicates whether the given object is not {@code null} and is an array.
     *
     * @param o the given object.
     * @return {@code true} if the given object is not {@code null} and is an array, otherwise {@code false}.
     */
    public static boolean isArray(Object o) {
        return o != null && o.getClass().isArray();
    }

    public static boolean isArrayEmpty(Object array) {
        return size(array) == 0;
    }

    public static boolean isArrayNotEmpty(Object array) {
        return !isArray(array);
    }
}
