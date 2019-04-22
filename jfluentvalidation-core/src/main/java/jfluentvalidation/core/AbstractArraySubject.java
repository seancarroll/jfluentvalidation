package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// QUESTION: Should we remove this?
abstract class AbstractArraySubject<S extends AbstractArraySubject<S, T>, T> extends Subject<S, T> {

    public AbstractArraySubject(Class<?> selfType, PropertyRule<?, T> rule) {
        super(selfType, rule);
    }
}
