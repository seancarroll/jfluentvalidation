package jfluentvalidation.constraints.numbers;

public class IsNotCloseToByteConstraint<T> extends AbstractIsNotCloseToConstraint<T, Byte> {

    public IsNotCloseToByteConstraint(Byte other, Byte offset) {
        super(other, offset);
    }

    public IsNotCloseToByteConstraint(Byte other, Byte offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Byte absDiff(Byte actual, Byte other) {
        return (byte) Math.abs(actual - other);
    }
}
