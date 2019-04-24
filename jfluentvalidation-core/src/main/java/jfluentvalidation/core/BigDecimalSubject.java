package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.math.BigDecimal;

/**
 *
 * @param <T>
 */
public class BigDecimalSubject<T> extends AbstractComparableNumber<BigDecimalSubject<T>, T, BigDecimal> {

    public BigDecimalSubject(PropertyRule<T, BigDecimal> rule) {
        super(BigDecimalSubject.class, rule);
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
