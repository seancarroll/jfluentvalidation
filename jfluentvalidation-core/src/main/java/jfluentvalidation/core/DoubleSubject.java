package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class DoubleSubject extends AbstractComparableNumber<DoubleSubject, Double> {

    public DoubleSubject(PropertyRule<?, Double> rule) {
        super(DoubleSubject.class, rule);
    }
}
