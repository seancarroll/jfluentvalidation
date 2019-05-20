package jfluentvalidation.constraints.array.length;

public class ByteArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, byte[]> {

    public ByteArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }
}
