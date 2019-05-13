package jfluentvalidation.constraints.array.sizeas;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.function.IntSupplier;

public class HasSameSizeAsByteArrayConstraint<T> implements Constraint<T, byte[]> {

    private final IntSupplier sizeSupplier;

    public HasSameSizeAsByteArrayConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSameSizeAsByteArrayConstraint(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.sizeSupplier = () -> Arrays.size(other);
    }

    public HasSameSizeAsByteArrayConstraint(int size) {
        Ensure.positive(size, "size");
        this.sizeSupplier = () -> size;
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        return context.getPropertyValue().length == sizeSupplier.getAsInt();
    }
}
