package jfluentvalidation.core;

import jfluentvalidation.constraints.numbers.IsCloseToBigIntegerConstraint;
import jfluentvalidation.constraints.numbers.IsNotCloseToBigIntegerConstraint;
import jfluentvalidation.rules.PropertyRule;

import java.math.BigInteger;

/**
 * Constraints for {@link BigInteger} typed subjects.
 *
 * @param <T>  the type of the instance
 */
public class BigIntegerSubject<T> extends AbstractComparableNumber<BigIntegerSubject<T>, T, BigInteger> {

    public BigIntegerSubject(PropertyRule<T, BigInteger> rule) {
        super(BigIntegerSubject.class, rule);
    }

    @Override
    public BigIntegerSubject<T> isCloseTo(BigInteger expected, BigInteger offset, boolean strict) {
        rule.addConstraint(new IsCloseToBigIntegerConstraint<>(expected, offset, strict));
        return myself;
    }

    @Override
    public BigIntegerSubject<T> isNotCloseTo(BigInteger expected, BigInteger offset, boolean strict) {
        rule.addConstraint(new IsNotCloseToBigIntegerConstraint<>(expected, offset, strict));
        return myself;
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
