package jfluentvalidation.constraints.array.length;

public class ByteArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, byte[]> {

    public ByteArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
