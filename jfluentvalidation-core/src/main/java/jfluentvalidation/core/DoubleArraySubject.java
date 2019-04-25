package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

public class DoubleArraySubject<T> extends Subject<DoubleArraySubject<T>, T, double[]> {

    public DoubleArraySubject(PropertyRule<T, double[]> rule) {
        super(DoubleArraySubject.class, rule);
    }

}
