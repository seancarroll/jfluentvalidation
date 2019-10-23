package jfluentvalidation.constraints.numbers;

public class IsCloseToFloatConstraint<T> extends AbstractIsCloseConstraint<T, Float> {

    public IsCloseToFloatConstraint(Float other, Float offset) {
        super(other, offset);
    }

    public IsCloseToFloatConstraint(Float other, Float offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Float absDiff(Float actual, Float other) {
        return Math.abs(actual - other);
    }
}
