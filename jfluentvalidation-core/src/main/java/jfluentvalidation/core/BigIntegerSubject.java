package jfluentvalidation.core;

import jfluentvalidation.rules.PropertyRule;

import java.math.BigInteger;

/**
 *
 * @param <T>  the type of the instance
 */
public class BigIntegerSubject<T> extends AbstractComparableNumber<BigIntegerSubject<T>, T, BigInteger> {

    public BigIntegerSubject(PropertyRule<T, BigInteger> rule) {
        super(BigIntegerSubject.class, rule);
    }

    @Override
    protected BigInteger zero() {
        return BigInteger.ZERO;
    }

    @Override
    protected BigInteger one() {
        return BigInteger.ONE;
    }
}
