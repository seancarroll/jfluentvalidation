package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToIntegerConstraint;
import jfluentvalidation.constraints.numbers.IsNotCloseToIntegerConstraint;
import jfluentvalidation.rules.PropertyRule;

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
    public IntegerSubject<T> isCloseTo(Integer expected, Integer offset, boolean strict) {
        rule.addConstraint(new IsCloseToIntegerConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public IntegerSubject<T> isNotCloseTo(Integer expected, Integer offset, boolean strict) {
        rule.addConstraint(new IsNotCloseToIntegerConstraint<>(expected, offset, strict));
        return myself;
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
