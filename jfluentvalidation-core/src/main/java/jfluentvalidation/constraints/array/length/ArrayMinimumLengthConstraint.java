package jfluentvalidation.constraints.array.length;

public class ArrayMinimumLengthConstraint<T, A> extends ArrayLengthConstraint<T, A> {

    public ArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
