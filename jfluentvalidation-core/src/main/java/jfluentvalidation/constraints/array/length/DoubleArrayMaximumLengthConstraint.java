package jfluentvalidation.constraints.array.length;

public class DoubleArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, double[]> {

    public DoubleArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }
}
