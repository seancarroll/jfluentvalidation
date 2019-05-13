package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

public abstract class AbstractIntArraySubject<S extends AbstractIntArraySubject<S, T>, T>
    extends AbstractArraySubject<S, T, int[], Integer> {

    public AbstractIntArraySubject(Class<?> selfType, PropertyRule<T, int[]> rule) {
        super(selfType, rule);
    }
}
