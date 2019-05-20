package jfluentvalidation.constraints.array.length;

public class BooleanArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, boolean[]> {

    public BooleanArrayExactLengthConstraint(int length) {
        super(length, length);
    }
}
