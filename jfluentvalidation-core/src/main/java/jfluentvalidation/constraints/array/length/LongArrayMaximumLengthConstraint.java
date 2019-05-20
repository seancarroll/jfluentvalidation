package jfluentvalidation.constraints.array.length;

public class LongArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, long[]> {

    public LongArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
