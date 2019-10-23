package jfluentvalidation.constraints.numbers;

public class IsCloseToLongConstraint<T> extends AbstractIsCloseConstraint<T, Long> {

    public IsCloseToLongConstraint(Long other, Long offset) {
        super(other, offset);
    }

    public IsCloseToLongConstraint(Long other, Long offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Long absDiff(Long actual, Long other) {
        return Math.abs(actual - other);
    }
}
