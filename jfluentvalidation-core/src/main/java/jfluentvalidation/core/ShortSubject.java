package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: verify we can handle primitives

/**
 * Constraints for {@code short} subjects.
 *
 * @param <T>  the type of the instance
 */
public class ShortSubject<T> extends AbstractComparableNumber<ShortSubject<T>, T, Short> {

    public ShortSubject(PropertyRule<T, Short> rule) {
        super(ShortSubject.class, rule);
    }

    @Override
    protected Short zero() {
        return 0;
    }

    @Override
    protected Short one() {
        return 1;
    }
}
