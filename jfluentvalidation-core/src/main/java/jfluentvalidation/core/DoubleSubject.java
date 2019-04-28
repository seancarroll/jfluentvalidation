package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: verify we can handle primitives

/**
 *
 * @param <T>  the type of the instance
 */
public class DoubleSubject<T> extends AbstractComparableNumber<DoubleSubject<T>, T, Double> {

    public DoubleSubject(PropertyRule<T, Double> rule) {
        super(DoubleSubject.class, rule);
    }

    @Override
    protected Double zero() {
        return 0.0d;
    }

    @Override
    protected Double one() {
        return 1.0d;
    }
}
