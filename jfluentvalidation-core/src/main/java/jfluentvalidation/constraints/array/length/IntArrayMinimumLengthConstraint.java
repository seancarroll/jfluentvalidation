package jfluentvalidation.constraints.array.length;

public class IntArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, int[]> {

    public IntArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
