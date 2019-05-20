package jfluentvalidation.constraints.array.length;

public class DoubleArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, double[]> {

    public DoubleArrayExactLengthConstraint(int length) {
        super(length, length);
    }

}
