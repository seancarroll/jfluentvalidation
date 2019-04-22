package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class FloatSubject extends AbstractComparableNumber<FloatSubject, Float> {

    public FloatSubject(PropertyRule<?, Float> rule) {
        super(FloatSubject.class, rule);
    }
}
