package jfluentvalidation.constraints.array.sizeas;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class HasSameSizeAsBooleanArrayConstraint<T> implements Constraint<T, boolean[]> {

    private final IntSupplier sizeSupplier;

    public HasSameSizeAsBooleanArrayConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSameSizeAsBooleanArrayConstraint(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.sizeSupplier = () -> Array.getLength(other);
    }

    public HasSameSizeAsBooleanArrayConstraint(int size) {
        Ensure.positive(size, "length");
        this.sizeSupplier = () -> size;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        return context.getPropertyValue().length == sizeSupplier.getAsInt();
    }
}
