package jfluentvalidation.constraints.array.length;

public class CharArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, char[]> {

    public CharArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
