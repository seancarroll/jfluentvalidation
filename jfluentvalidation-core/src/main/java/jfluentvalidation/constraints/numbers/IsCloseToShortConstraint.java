package jfluentvalidation.constraints.numbers;

public class IsCloseToShortConstraint<T> extends AbstractIsCloseToConstraint<T, Short> {

    public IsCloseToShortConstraint(Short other, Short offset) {
        super(other, offset);
    }

    public IsCloseToShortConstraint(Short other, Short offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Short absDiff(Short actual, Short other) {
        return (short) Math.abs(actual - other);
    }
}
