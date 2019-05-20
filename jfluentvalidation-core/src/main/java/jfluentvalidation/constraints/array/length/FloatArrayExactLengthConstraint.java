package jfluentvalidation.constraints.array.length;

public class FloatArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, float[]> {

    public FloatArrayExactLengthConstraint(int length) {
        super(length, length);
    }

}
