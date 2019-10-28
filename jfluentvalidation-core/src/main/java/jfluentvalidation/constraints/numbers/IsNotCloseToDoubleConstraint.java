package jfluentvalidation.constraints.numbers;

public class IsNotCloseToDoubleConstraint<T> extends AbstractIsNotCloseToConstraint<T, Double> {

    public IsNotCloseToDoubleConstraint(Double other, Double offset) {
        super(other, offset);
    }

    public IsNotCloseToDoubleConstraint(Double other, Double offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Double absDiff(Double actual, Double other) {
        return Math.abs(actual - other);
    }
}
