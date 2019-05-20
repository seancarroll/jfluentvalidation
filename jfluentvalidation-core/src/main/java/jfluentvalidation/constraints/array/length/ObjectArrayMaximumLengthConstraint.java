package jfluentvalidation.constraints.array.length;

public class ObjectArrayMaximumLengthConstraint<T> extends ArrayLengthConstraint<T, Object[]> {

    public ObjectArrayMaximumLengthConstraint(int max) {
        super(0, max);
    }

}
