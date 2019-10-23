package jfluentvalidation.constraints.numbers;

import java.math.BigInteger;

public class IsCloseToBigIntegerConstraint<T> extends AbstractIsCloseConstraint<T, BigInteger> {

    public IsCloseToBigIntegerConstraint(BigInteger other, BigInteger offset) {
        super(other, offset);
    }

    public IsCloseToBigIntegerConstraint(BigInteger other, BigInteger offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected BigInteger absDiff(BigInteger actual, BigInteger other) {
        return actual.subtract(other).abs();
    }
}
