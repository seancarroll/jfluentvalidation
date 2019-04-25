package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

public class LongArraySubject<T> extends Subject<LongArraySubject<T>, T, long[]> {

    public LongArraySubject(PropertyRule<T, long[]> rule) {
        super(LongArraySubject.class, rule);
    }

}
