package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

// TODO: what should the type hierarchy be?

/**
 * Constraints for {@code int} subjects.
 *
 * @param <T>  the type of the instance
 */
public class IntegerSubject<T> extends AbstractComparableNumber<IntegerSubject<T>, T, Integer> {

    public IntegerSubject(PropertyRule<T, Integer> rule) {
        super(IntegerSubject.class, rule);
    }

    @Override
    protected Integer zero() {
        return 0;
    }

    @Override
    protected Integer one() {
        return 1;
    }
}
