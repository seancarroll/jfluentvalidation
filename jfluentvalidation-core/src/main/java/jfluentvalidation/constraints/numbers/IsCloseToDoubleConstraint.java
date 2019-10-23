package jfluentvalidation.constraints.numbers;

public class IsCloseToDoubleConstraint<T> extends AbstractIsCloseConstraint<T, Double> {

    public IsCloseToDoubleConstraint(Double other, Double offset) {
        super(other, offset);
    }

    public IsCloseToDoubleConstraint(Double other, Double offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Double absDiff(Double actual, Double other) {
        return Math.abs(actual - other);
    }
}
