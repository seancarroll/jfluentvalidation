package jfluentvalidation.constraints.array.length;

public class CharArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, char[]> {

    public CharArrayExactLengthConstraint(int length) {
        super(length, length);
    }
}
