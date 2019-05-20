package jfluentvalidation.constraints.array.length;

public class CharArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, char[]> {

    public CharArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
