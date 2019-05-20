package jfluentvalidation.constraints.array.length;

public class BooleanArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, boolean[]> {

    public BooleanArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }
}
