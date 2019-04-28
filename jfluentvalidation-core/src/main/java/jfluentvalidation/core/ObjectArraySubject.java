package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>  the type of the instance
 * @param <E>  the type of the element in the array
 */
public class ObjectArraySubject<T, E> extends AbstractArraySubject<ObjectArraySubject<T, E>, T, E> {

    public ObjectArraySubject(PropertyRule<T, E> rule) {
        super(ObjectArraySubject.class, rule);
    }
}
