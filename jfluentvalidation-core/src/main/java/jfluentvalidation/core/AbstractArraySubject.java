package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// QUESTION: Should we remove this?
abstract class AbstractArraySubject<S extends AbstractArraySubject<S, T, A>, T, A> extends Subject<S, T, A[]> {

    public AbstractArraySubject(Class<?> selfType, PropertyRule<T, A[]> rule) {
        super(selfType, rule);
    }

}
