package jfluentvalidation.constraints.array.length;

public class ByteArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, byte[]> {

    public ByteArrayExactLengthConstraint(int length) {
        super(length, length);
    }

}
