package jfluentvalidation.constraints.numbers;

import java.math.BigInteger;

public class IsNotCloseToBigIntegerConstraint<T> extends AbstractIsNotCloseToConstraint<T, BigInteger> {

    public IsNotCloseToBigIntegerConstraint(BigInteger other, BigInteger offset) {
        super(other, offset);
    }

    public IsNotCloseToBigIntegerConstraint(BigInteger other, BigInteger offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected BigInteger absDiff(BigInteger actual, BigInteger other) {
        return actual.subtract(other).abs();
    }
}
