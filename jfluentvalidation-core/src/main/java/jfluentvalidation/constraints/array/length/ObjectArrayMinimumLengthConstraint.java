package jfluentvalidation.constraints.array.length;

public class ObjectArrayMinimumLengthConstraint<T> extends ArrayLengthConstraint<T, Object[]> {

    public ObjectArrayMinimumLengthConstraint(int min) {
        super(min, -1);
    }

}
