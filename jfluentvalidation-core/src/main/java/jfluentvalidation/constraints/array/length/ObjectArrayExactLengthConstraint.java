package jfluentvalidation.constraints.array.length;

public class ObjectArrayExactLengthConstraint<T> extends ArrayLengthConstraint<T, Object[]> {

    public ObjectArrayExactLengthConstraint(int length) {
        super(length, length);
    }

}
