package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class ObjectSubject extends Subject<ObjectSubject, Object> {

    public ObjectSubject(PropertyRule<?, Object> rule) {
        super(ObjectSubject.class, rule);
    }
}
