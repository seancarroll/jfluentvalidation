package jfluentvalidation.common;

import java.util.ArrayList;

/**
 *
 */
public class Lists {

    private Lists() {
        // statics only
    }

    /**
     *
     * @param elements
     * @param <E>
     * @return
     */
    public static <E> ArrayList<E> newArrayList(Iterable<? extends E> elements) {
        ArrayList<E> list = new ArrayList<>();
        elements.iterator().forEachRemaining(list::add);
        return list;
    }

}
