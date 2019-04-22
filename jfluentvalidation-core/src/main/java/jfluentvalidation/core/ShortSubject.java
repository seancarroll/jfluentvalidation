package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class ShortSubject extends AbstractComparableNumber<ShortSubject, Short> {

    public ShortSubject(PropertyRule<?, Short> rule) {
        super(ShortSubject.class, rule);
    }
}
