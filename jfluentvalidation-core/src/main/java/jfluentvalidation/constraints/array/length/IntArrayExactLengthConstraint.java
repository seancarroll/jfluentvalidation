package jfluentvalidation.constraints.array.length;

public class IntArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, int[]> {

    public IntArrayExactLengthConstraint(int length) {
        super(length, length);
    }

}
