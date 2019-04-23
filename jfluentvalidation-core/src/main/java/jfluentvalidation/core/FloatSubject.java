package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class FloatSubject<T> extends AbstractComparableNumber<FloatSubject<T>, T, Float> {

    public FloatSubject(PropertyRule<T, Float> rule) {
        super(FloatSubject.class, rule);
    }
}
