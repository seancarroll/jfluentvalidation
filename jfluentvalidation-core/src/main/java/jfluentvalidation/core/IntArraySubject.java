package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

public class IntArraySubject<T> extends Subject<IntArraySubject<T>, T, int[]> {

    public IntArraySubject(PropertyRule<T, int[]> rule) {
        super(IntArraySubject.class, rule);
    }

}
