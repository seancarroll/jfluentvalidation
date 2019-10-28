package jfluentvalidation.constraints.numbers;

public class IsNotCloseToFloatConstraint<T> extends AbstractIsNotCloseToConstraint<T, Float> {

    public IsNotCloseToFloatConstraint(Float other, Float offset) {
        super(other, offset);
    }

    public IsNotCloseToFloatConstraint(Float other, Float offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Float absDiff(Float actual, Float other) {
        return Math.abs(actual - other);
    }
}
