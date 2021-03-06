package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToShortConstraint;
import jfluentvalidation.constraints.numbers.IsNotCloseToShortConstraint;
import jfluentvalidation.rules.PropertyRule;

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
    public ShortSubject<T> isCloseTo(Short expected, Short offset, boolean strict) {
        rule.addConstraint(new IsCloseToShortConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public ShortSubject<T> isNotCloseTo(Short expected, Short offset, boolean strict) {
        rule.addConstraint(new IsNotCloseToShortConstraint<>(expected, offset, strict));
        return myself;
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
