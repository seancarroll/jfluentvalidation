package jfluentvalidation.constraints.array.sizeas;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.function.IntSupplier;

public class HasSameSizeAsFloatArrayConstraint<T> implements Constraint<T, float[]> {

    private final IntSupplier sizeSupplier;

    public HasSameSizeAsFloatArrayConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSameSizeAsFloatArrayConstraint(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.sizeSupplier = () -> Arrays.size(other);
    }

    public HasSameSizeAsFloatArrayConstraint(int size) {
        Ensure.positive(size, "size");
        this.sizeSupplier = () -> size;
    }

    @Override
    public boolean isValid(RuleContext<T, float[]> context) {
        return context.getPropertyValue().length == sizeSupplier.getAsInt();
    }
}
