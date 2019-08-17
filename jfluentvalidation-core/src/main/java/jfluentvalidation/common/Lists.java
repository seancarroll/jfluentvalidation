package jfluentvalidation.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class Lists {

    private Lists() {
        // statics only
    }

    public static <E> List<E> newArrayList(Iterable<? extends E> elements) {
        return (elements instanceof Collection)
            ? new ArrayList<>((Collection<E>)elements)
            : newArrayList(elements.iterator());
    }

    /**
     *
     * @param elements
     * @param <E>
     * @return
     */
    public static <E> List<E> newArrayList(Iterator<? extends E> elements) {
        ArrayList<E> list = new ArrayList<>();
        elements.forEachRemaining(list::add);
        return list;
    }

}
