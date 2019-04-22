package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: what should the type hierarchy be?

/**
 *
 */
public class IntegerSubject extends AbstractComparableNumber<IntegerSubject, Integer> {

    public IntegerSubject(PropertyRule<?, Integer> rule) {
        super(IntegerSubject.class, rule);
    }
}
