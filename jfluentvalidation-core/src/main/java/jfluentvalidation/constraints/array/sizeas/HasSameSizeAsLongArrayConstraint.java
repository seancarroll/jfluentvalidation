package jfluentvalidation.constraints.array.sizeas;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.function.IntSupplier;

public class HasSameSizeAsLongArrayConstraint<T> implements Constraint<T, long[]> {

    private final IntSupplier sizeSupplier;

    public HasSameSizeAsLongArrayConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSameSizeAsLongArrayConstraint(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.sizeSupplier = () -> Arrays.size(other);
    }

    public HasSameSizeAsLongArrayConstraint(int size) {
        Ensure.positive(size, "size");
        this.sizeSupplier = () -> size;
    }

    @Override
    public boolean isValid(RuleContext<T, long[]> context) {
        return context.getPropertyValue().length == sizeSupplier.getAsInt();
    }
}
