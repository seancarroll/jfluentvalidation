package jfluentvalidation.constraints.numbers;

import java.math.BigDecimal;

public class IsNotCloseToBigDecimalConstraint<T> extends AbstractIsNotCloseToConstraint<T, BigDecimal> {

    public IsNotCloseToBigDecimalConstraint(BigDecimal other, BigDecimal offset) {
        super(other, offset);
    }

    public IsNotCloseToBigDecimalConstraint(BigDecimal other, BigDecimal offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected BigDecimal absDiff(BigDecimal actual, BigDecimal other) {
        return actual.subtract(other).abs();
    }
}
