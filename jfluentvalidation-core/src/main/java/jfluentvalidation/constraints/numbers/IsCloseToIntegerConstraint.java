package jfluentvalidation.constraints.numbers;

public class IsCloseToIntegerConstraint<T> extends AbstractIsCloseConstraint<T, Integer> {

    public IsCloseToIntegerConstraint(Integer other, Integer offset) {
        super(other, offset);
    }

    public IsCloseToIntegerConstraint(Integer other, Integer offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Integer absDiff(Integer actual, Integer other) {
        return Math.abs(actual - other);
    }
}
