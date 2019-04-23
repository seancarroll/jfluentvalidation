package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class DoubleSubject<T> extends AbstractComparableNumber<DoubleSubject<T>, T, Double> {

    public DoubleSubject(PropertyRule<T, Double> rule) {
        super(DoubleSubject.class, rule);
    }
}
