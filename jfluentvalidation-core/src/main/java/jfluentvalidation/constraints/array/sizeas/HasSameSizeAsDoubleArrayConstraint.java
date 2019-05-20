package jfluentvalidation.constraints.array.sizeas;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class HasSameSizeAsDoubleArrayConstraint<T> implements Constraint<T, double[]> {

    private final IntSupplier sizeSupplier;

    public HasSameSizeAsDoubleArrayConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSameSizeAsDoubleArrayConstraint(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.sizeSupplier = () -> Array.getLength(other);
    }

    public HasSameSizeAsDoubleArrayConstraint(int size) {
        Ensure.positive(size, "length");
        this.sizeSupplier = () -> size;
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        return context.getPropertyValue().length == sizeSupplier.getAsInt();
    }
}
