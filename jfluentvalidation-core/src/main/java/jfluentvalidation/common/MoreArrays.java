package jfluentvalidation.common;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import static jfluentvalidation.common.Comparables.*;

/**
 *
 */
public final class MoreArrays {

    private MoreArrays() {
        // statics only
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

    // https://bugs.openjdk.java.net/browse/JDK-8051447
    public static boolean isArrayEmpty(Object array) {
        return Array.getLength(array) == 0;
    }

    public static boolean isArrayNotEmpty(Object array) {
        return !isArrayEmpty(array);
    }


    public static boolean contains(boolean[] array, boolean target) {
        for (boolean value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(byte[] array, byte target) {
        for (byte value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(char[] array, char target) {
        for (char value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(double[] array, double target) {
        for (double value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(float[] array, float target) {
        for (float value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(int[] array, int target) {
        for (int value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(long[] array, long target) {
        for (long value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(short[] array, short target) {
        for (short value : array) {
            if (value == target) {
                return true;
            }
        }
        return false;
    }

    public static boolean contains(Object[] arr, Object x) {
        for (Object elem : arr) {
            if (elem.equals(x)) {
                return true;
            }
        }
        return false;
    }

    @SafeVarargs
    public static <T> T[] array(T... values) {
        return values;
    }

    public static <T> T[] newArray(Class<T> type, int length) {
        return (T[]) Array.newInstance(type, length);
    }

    public static <T> boolean contains2(final T[] array, final T v) {
        // TODO: simplify
        if (v == null) {
            for (final T e : array)
                if (e == null)
                    return true;
        }
        else {
            for (final T e : array)
                if (e == v || v.equals(e))
                    return true;
        }

        return false;
    }

    public static boolean hasDuplicates(Object[] arr) {
        return new HashSet<>(Arrays.asList(arr)).size() < arr.length;
    }

    public static boolean hasMinLength(int actual, int min) {
        return hasLengthBetween(actual, min, -1);
    }

    public static boolean hasMaxLength(int actual, int max) {
        return hasLengthBetween(actual, 0, max);
    }

    public static boolean hasLengthBetween(int actual, int min, int max) {
        return hasLengthBetween(actual, min, max, true, true);
    }

    public static boolean hasLengthBetween(int actual, int min, int max, boolean includeMin, boolean includeMax) {

        boolean checkLowerBoundaryRange = includeMin ? !isGreaterThan(min, actual) : isLessThan(min, actual);
        if (!checkLowerBoundaryRange) {
            return false;
        }

        boolean isGreaterThanMax = includeMax ? isGreaterThan(actual, max) : isGreaterThanOrEqual(actual, max);
        if (isGreaterThanMax && max != -1) {
            return false;
        }

        return true;
    }
}
