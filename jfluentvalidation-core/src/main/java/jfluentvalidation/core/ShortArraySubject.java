package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

public class ShortArraySubject<T> extends Subject<ShortArraySubject<T>, T, short[]> {

    public ShortArraySubject(PropertyRule<T, short[]> rule) {
        super(ShortArraySubject.class, rule);
    }

}
