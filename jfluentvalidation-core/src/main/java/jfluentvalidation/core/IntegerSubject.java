package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: what should the type hierarchy be?

/**
 *
 */
public class IntegerSubject<T> extends AbstractComparableNumber<IntegerSubject<T>, T, Integer> {

    public IntegerSubject(PropertyRule<T, Integer> rule) {
        super(IntegerSubject.class, rule);
    }
}
