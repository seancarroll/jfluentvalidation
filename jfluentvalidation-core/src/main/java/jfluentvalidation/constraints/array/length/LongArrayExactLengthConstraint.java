package jfluentvalidation.constraints.array.length;

public class LongArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, long[]> {

    public LongArrayExactLengthConstraint(int length) {
        super(length, length);
    }

}
