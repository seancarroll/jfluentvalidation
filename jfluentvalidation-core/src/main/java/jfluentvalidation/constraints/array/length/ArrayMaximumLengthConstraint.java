package jfluentvalidation.constraints.array.length;

public class ArrayMaximumLengthConstraint<T, A> extends ArrayLengthConstraint<T, A> {

    public ArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
