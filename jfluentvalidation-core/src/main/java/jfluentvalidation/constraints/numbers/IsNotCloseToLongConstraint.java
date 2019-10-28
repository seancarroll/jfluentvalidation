package jfluentvalidation.constraints.numbers;

public class IsNotCloseToLongConstraint<T> extends AbstractIsNotCloseToConstraint<T, Long> {

    public IsNotCloseToLongConstraint(Long other, Long offset) {
        super(other, offset);
    }

    public IsNotCloseToLongConstraint(Long other, Long offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Long absDiff(Long actual, Long other) {
        return Math.abs(actual - other);
    }
}
