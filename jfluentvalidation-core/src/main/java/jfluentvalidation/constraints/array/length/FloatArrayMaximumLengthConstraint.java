package jfluentvalidation.constraints.array.length;

public class FloatArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, float[]> {

    public FloatArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
