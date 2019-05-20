package jfluentvalidation.constraints.array.length;

public class DoubleArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, double[]> {

    public DoubleArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
