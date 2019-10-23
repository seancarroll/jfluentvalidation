package jfluentvalidation.constraints.numbers;

import java.math.BigDecimal;

public class IsCloseToBigDecimalConstraint<T> extends AbstractIsCloseConstraint<T, BigDecimal> {

    public IsCloseToBigDecimalConstraint(BigDecimal other, BigDecimal offset) {
        super(other, offset);
    }

    public IsCloseToBigDecimalConstraint(BigDecimal other, BigDecimal offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected BigDecimal absDiff(BigDecimal actual, BigDecimal other) {
        return actual.subtract(other).abs();
    }
}
