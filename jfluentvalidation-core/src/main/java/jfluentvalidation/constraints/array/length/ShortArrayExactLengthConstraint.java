package jfluentvalidation.constraints.array.length;

public class ShortArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, short[]> {

    public ShortArrayExactLengthConstraint(int length) {
        super(length, length);
    }

}
