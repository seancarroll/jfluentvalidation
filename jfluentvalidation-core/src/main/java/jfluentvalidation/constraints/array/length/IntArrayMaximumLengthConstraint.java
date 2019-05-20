package jfluentvalidation.constraints.array.length;

public class IntArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, int[]> {

    public IntArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
