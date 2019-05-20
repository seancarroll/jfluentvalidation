package jfluentvalidation.constraints.array;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class HasSameSizeAsConstraint<T, A> implements Constraint<T, A[]> {

    private final IntSupplier sizeSupplier;

    public HasSameSizeAsConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSameSizeAsConstraint(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.sizeSupplier = () -> Array.getLength(other);
    }

    @Override
    public boolean isValid(RuleContext<T, A[]> context) {
        return context.getPropertyValue().length == sizeSupplier.getAsInt();
    }
}

