package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

/**
 *
 */
public class ShortSubject<T> extends AbstractComparableNumber<ShortSubject<T>, T, Short> {

    public ShortSubject(PropertyRule<T, Short> rule) {
        super(ShortSubject.class, rule);
    }
}
