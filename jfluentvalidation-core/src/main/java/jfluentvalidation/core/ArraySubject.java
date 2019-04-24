package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>
 * @param <E>
 */
public class ArraySubject<T, E> extends AbstractArraySubject<ArraySubject<T, E>, T, E> {

    public ArraySubject(PropertyRule<T, E> rule) {
        super(ArraySubject.class, rule);
    }
}
