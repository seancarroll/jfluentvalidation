package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToLongConstraint;
import jfluentvalidation.rules.PropertyRule;

/**
 * Constraints for {@code long} subjects.
 *
 * @param <T>  the type of the instance
 */
public class LongSubject<T> extends AbstractComparableNumber<LongSubject<T>, T, Long> {

    public LongSubject(PropertyRule<T, Long> rule) {
        super(LongSubject.class, rule);
    }

    @Override
    public LongSubject<T> isCloseTo(Long expected, Long offset, boolean strict) {
        rule.addConstraint(new IsCloseToLongConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public LongSubject<T> isNotCloseTo(Long expected, Long offset, boolean strict) {
        // TODO: implement
        // rule.addConstraint(new IsNotCloseToLongConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    protected Long zero() {
        return 0L;
    }

    @Override
    protected Long one() {
        return 1L;
    }
}
