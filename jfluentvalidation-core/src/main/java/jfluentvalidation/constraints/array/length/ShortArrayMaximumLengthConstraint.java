package jfluentvalidation.constraints.array.length;

public class ShortArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, short[]> {

    public ShortArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
