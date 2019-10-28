package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToBigDecimalConstraint;
import jfluentvalidation.constraints.numbers.IsNotCloseToBigDecimalConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.math.BigDecimal;

/**
 * Constraints for {@link BigDecimal} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class BigDecimalSubject<T> extends AbstractComparableNumber<BigDecimalSubject<T>, T, BigDecimal> {

    public BigDecimalSubject(PropertyRule<T, BigDecimal> rule) {
        super(BigDecimalSubject.class, rule);
    }

    @Override
    public BigDecimalSubject<T> isCloseTo(BigDecimal expected, BigDecimal offset, boolean strict) {
        rule.addConstraint(new IsCloseToBigDecimalConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public BigDecimalSubject<T> isNotCloseTo(BigDecimal expected, BigDecimal offset, boolean strict) {
        rule.addConstraint(new IsNotCloseToBigDecimalConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    protected BigDecimal zero() {
        return BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal one() {
        return BigDecimal.ONE;
    }
}
