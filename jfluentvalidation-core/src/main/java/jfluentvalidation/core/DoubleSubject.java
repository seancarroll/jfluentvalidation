package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToDoubleConstraint;
import jfluentvalidation.rules.PropertyRule;

// TODO: verify we can handle primitives

/**
 * Constraints for {@link Double} subjects.
 *
 * @param <T>  the type of the instance
 */
public class DoubleSubject<T> extends AbstractComparableNumber<DoubleSubject<T>, T, Double> {

    public DoubleSubject(PropertyRule<T, Double> rule) {
        super(DoubleSubject.class, rule);
    }

    @Override
    public DoubleSubject<T> isCloseTo(Double expected, Double offset, boolean strict) {
        rule.addConstraint(new IsCloseToDoubleConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public DoubleSubject<T> isNotCloseTo(Double expected, Double offset, boolean strict) {
        // TODO: implement
        // rule.addConstraint(new IsNotCloseToDoubleConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    protected Double zero() {
        return 0.0d;
    }

    @Override
    protected Double one() {
        return 1.0d;
    }
}
