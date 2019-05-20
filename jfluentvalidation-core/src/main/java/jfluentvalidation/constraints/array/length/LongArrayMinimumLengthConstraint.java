package jfluentvalidation.constraints.array.length;

public class LongArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, long[]> {

    public LongArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
