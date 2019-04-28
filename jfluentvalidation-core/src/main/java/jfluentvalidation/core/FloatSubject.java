package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: verify we can handle primitives

/**
 *
 * @param <T>  the type of the instance
 */
public class FloatSubject<T> extends AbstractComparableNumber<FloatSubject<T>, T, Float> {

    public FloatSubject(PropertyRule<T, Float> rule) {
        super(FloatSubject.class, rule);
    }

    @Override
    protected Float zero() {
        return 0.0f;
    }

    @Override
    protected Float one() {
        return 1.0f;
    }
}
