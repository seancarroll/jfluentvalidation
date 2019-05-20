package jfluentvalidation.constraints.array.length;

public class BooleanArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, boolean[]> {

    public BooleanArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }
}
