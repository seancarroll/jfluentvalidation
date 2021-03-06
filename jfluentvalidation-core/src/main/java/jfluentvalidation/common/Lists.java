package jfluentvalidation.common;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Chars;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.common.primitives.Shorts;
import jfluentvalidation.internal.Ensure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static java.lang.reflect.Array.getLength;

/**
 *
 */
public class Lists {

    private Lists() {
        // statics only
    }

    /**
     * Creates an {@code ArrayList} instance containing the given elements.
     *
     * @param elements  the given elements used to populate new List
     * @param <E>  the type of element
     * @return new ArrayList
     */
    @SafeVarargs
    public static <E> List<E> newArrayList(E... elements) {
        ArrayList<E> list = new ArrayList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }

    /**
     * Creates an {@code ArrayList} instance containing the given elements.
     *
     * @param elements  the given elements used to populate new List
     * @param <E>  the type of element
     * @return new ArrayList
     */
    public static <E> List<E> newArrayList(Iterable<? extends E> elements) {
        return (elements instanceof Collection)
            ? new ArrayList<>(MoreCollections.cast(elements))
            : newArrayList(elements.iterator());
    }

    /**
     * Creates an {@code ArrayList} instance containing the given elements.
     *
     * @param elements  the given elements used to populate new List
     * @param <E>  the type of element
     * @return new ArrayList
     */
    public static <E> List<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list = new ArrayList<>();
        elements.forEachRemaining(list::add);
        return list;
    }

    public static List<Boolean> asList(boolean[] booleans) {
        final List<Boolean> list = new ArrayList<>(booleans.length);
        for (boolean b : booleans) {
            list.add(b);
        }
        return list;
    }

    public static List<Byte> asList(byte[] bytes) {
        final List<Byte> list = new ArrayList<>(bytes.length);
        for (byte b : bytes) {
            list.add(b);
        }
        return list;
    }

    public static List<Character> asList(char[] chars) {
        final List<Character> list = new ArrayList<>(chars.length);
        for (char b : chars) {
            list.add(b);
        }
        return list;
    }

    public static List<Double> asList(double[] doubles) {
        final List<Double> list = new ArrayList<>(doubles.length);
        for (double b : doubles) {
            list.add(b);
        }
        return list;
    }

    public static List<Float> asList(float[] floats) {
        final List<Float> list = new ArrayList<>(floats.length);
        for (float b : floats) {
            list.add(b);
        }
        return list;
    }

    public static List<Integer> asList(int[] ints) {
        final List<Integer> list = new ArrayList<>(ints.length);
        for (int b : ints) {
            list.add(b);
        }
        return list;
    }

    public static List<Long> asList(long[] longs) {
        final List<Long> list = new ArrayList<>(longs.length);
        for (long b : longs) {
            list.add(b);
        }
        return list;
    }

    public static List<Short> asList(short[] shorts) {
        final List<Short> list = new ArrayList<>(shorts.length);
        for (short b : shorts) {
            list.add(b);
        }
        return list;
    }

    public static <T> List<T> asList(Object array) {
        Ensure.notNull(array);
        Ensure.isArray(array, "array");

        int length = getLength(array);
        List<T> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            list.add((T) Array.get(array, i));
        }
        return list;
    }

    public static List<?> toList(Object o) {
        Ensure.isArray(o, "object must be an array");

        Class<?> clazz = o.getClass().getComponentType();

        if (!clazz.isPrimitive()) {
            return Arrays.asList((Object[])o);
        }

        if (clazz == byte.class) {
            return Bytes.asList((byte[])o);
        } else if (clazz == char.class) {
            return Chars.asList((char[])o);
        } else if (clazz == double.class) {
            return Doubles.asList((double[])o);
        } else if (clazz == float.class) {
            return Floats.asList((float[])o);
        } else if (clazz == int.class) {
            return Ints.asList((int[])o);
        } else if (clazz == long.class) {
            return Longs.asList((long[])o);
        } else if (clazz == short.class) {
            return Shorts.asList((short[])o);
        } else if (clazz == boolean.class) {
            return Booleans.asList((boolean[])o);
        }

        throw new RuntimeException("could not convert object to list");
    }

}
