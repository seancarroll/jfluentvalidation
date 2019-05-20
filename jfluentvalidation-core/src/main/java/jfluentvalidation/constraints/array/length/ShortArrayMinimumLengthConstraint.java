package jfluentvalidation.constraints.array.length;

public class ShortArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, short[]> {

    public ShortArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
