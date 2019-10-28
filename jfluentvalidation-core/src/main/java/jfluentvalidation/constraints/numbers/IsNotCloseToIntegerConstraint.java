package jfluentvalidation.constraints.numbers;

public class IsNotCloseToIntegerConstraint<T> extends AbstractIsNotCloseToConstraint<T, Integer> {

    public IsNotCloseToIntegerConstraint(Integer other, Integer offset) {
        super(other, offset);
    }

    public IsNotCloseToIntegerConstraint(Integer other, Integer offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Integer absDiff(Integer actual, Integer other) {
        return Math.abs(actual - other);
    }
}
