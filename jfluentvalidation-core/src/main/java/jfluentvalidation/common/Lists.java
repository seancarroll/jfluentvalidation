package jfluentvalidation.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

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

}
