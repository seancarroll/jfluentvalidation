package jfluentvalidation.constraints.numbers;

public class IsCloseToByteConstraint<T> extends AbstractIsCloseConstraint<T, Byte> {

    public IsCloseToByteConstraint(Byte other, Byte offset) {
        super(other, offset);
    }

    public IsCloseToByteConstraint(Byte other, Byte offset, boolean strict) {
        super(other, offset, strict);
    }

    @Override
    protected Byte absDiff(Byte actual, Byte other) {
        return (byte) Math.abs(actual - other);
    }
}
