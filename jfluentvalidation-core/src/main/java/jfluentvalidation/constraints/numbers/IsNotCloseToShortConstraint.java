package jfluentvalidation.constraints.numbers;

public class IsNotCloseToShortConstraint<T> extends AbstractIsNotCloseToConstraint<T, Short> {

    public IsNotCloseToShortConstraint(Short other, Short offset) {
        super(other, offset);
    }

    public IsNotCloseToShortConstraint(Short other, Short offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Short absDiff(Short actual, Short other) {
        return (short) Math.abs(actual - other);
    }
}
