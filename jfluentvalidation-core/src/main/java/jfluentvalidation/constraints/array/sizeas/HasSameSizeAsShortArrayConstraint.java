package jfluentvalidation.constraints.array.sizeas;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class HasSameSizeAsShortArrayConstraint<T> implements Constraint<T, short[]> {

    private final IntSupplier sizeSupplier;

    public HasSameSizeAsShortArrayConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSameSizeAsShortArrayConstraint(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.sizeSupplier = () -> Array.getLength(other);
    }

    public HasSameSizeAsShortArrayConstraint(int size) {
        Ensure.positive(size, "length");
        this.sizeSupplier = () -> size;
    }

    @Override
    public boolean isValid(RuleContext<T, short[]> context) {
        return context.getPropertyValue().length == sizeSupplier.getAsInt();
    }
}
