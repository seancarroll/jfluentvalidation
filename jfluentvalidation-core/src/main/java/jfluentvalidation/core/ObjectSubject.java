package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class ObjectSubject<T> extends Subject<ObjectSubject<T>, T, Object> {

    public ObjectSubject(PropertyRule<T, Object> rule) {
        super(ObjectSubject.class, rule);
    }
}
