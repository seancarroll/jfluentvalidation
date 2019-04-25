package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

public class FloatArraySubject<T> extends Subject<FloatArraySubject<T>, T, float[]> {

    public FloatArraySubject(PropertyRule<T, float[]> rule) {
        super(FloatArraySubject.class, rule);
    }

}
