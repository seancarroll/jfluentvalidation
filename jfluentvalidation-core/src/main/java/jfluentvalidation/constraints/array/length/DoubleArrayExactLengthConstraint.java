package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class DoubleArrayExactLengthConstraint<T> implements Constraint<T, double[]> {

    private final IntSupplier lengthSupplier;

    public DoubleArrayExactLengthConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public DoubleArrayExactLengthConstraint(Object other) {
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public DoubleArrayExactLengthConstraint(int length) {
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        int len = context.getPropertyValue().length;
        return len == lengthSupplier.getAsInt();
    }
}
