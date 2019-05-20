package jfluentvalidation.constraints.array.length;

public class FloatArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, float[]> {

    public FloatArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
