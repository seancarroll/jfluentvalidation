package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

//public class ArrayExactLengthConstraint<T, A> extends ArrayLengthConstraint<T, A> {
//
//    public ArrayExactLengthConstraint(Iterable<?> other) {
//        this(() -> Iterables.size(other));
//    }
//
//    public ArrayExactLengthConstraint(Object other) {
//        this(() -> Array.getLength(other));
//    }
//
//    public ArrayExactLengthConstraint(int length) {
//        super(length, length);
//    }
//
//    public ArrayExactLengthConstraint(IntSupplier lengthSupplier) {
//        super(lengthSupplier, lengthSupplier);
//    }
//}

public class ArrayExactLengthConstraint<T, A> implements Constraint<T, A> {

    private IntSupplier lengthSupplier;

    public ArrayExactLengthConstraint(Iterable<?> other) {
        this(() -> Iterables.size(other));
    }

    public ArrayExactLengthConstraint(Object other) {
        this(() -> Array.getLength(other));
    }

    public ArrayExactLengthConstraint(int length) {
        this(() -> length);
    }

    public ArrayExactLengthConstraint(IntSupplier lengthSupplier) {
        this.lengthSupplier = lengthSupplier;
    }


    @Override
    public boolean isValid(RuleContext<T, A> context) {
        int len = Array.getLength(context.getPropertyValue());
        return len == lengthSupplier.getAsInt();
    }
}
