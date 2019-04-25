package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 * @param <T>
 * @param <E>
 */
public class ObjectArraySubject<T, E> extends AbstractArraySubject<ObjectArraySubject<T, E>, T, E> {

    public ObjectArraySubject(PropertyRule<T, E> rule) {
        super(ObjectArraySubject.class, rule);
    }
}
