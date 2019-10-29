package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToFloatConstraint;
import jfluentvalidation.constraints.numbers.IsNotCloseToFloatConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 * Constraints for {@code float} subjects.
 *
 * @param <T>  the type of the instance
 */
public class FloatSubject<T> extends AbstractComparableNumber<FloatSubject<T>, T, Float> {

    public FloatSubject(PropertyRule<T, Float> rule) {
        super(FloatSubject.class, rule);
    }

    @Override
    public FloatSubject<T> isCloseTo(Float expected, Float offset, boolean strict) {
        rule.addConstraint(new IsCloseToFloatConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public FloatSubject<T> isNotCloseTo(Float expected, Float offset, boolean strict) {
        rule.addConstraint(new IsNotCloseToFloatConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    protected Float zero() {
        return 0.0f;
    }

    @Override
    protected Float one() {
        return 1.0f;
    }
}
