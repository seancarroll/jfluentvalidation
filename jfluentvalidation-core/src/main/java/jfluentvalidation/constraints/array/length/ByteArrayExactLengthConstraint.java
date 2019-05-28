package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class ByteArrayExactLengthConstraint<T> implements Constraint<T, byte[]> {

    private final IntSupplier lengthSupplier;

    public ByteArrayExactLengthConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public ByteArrayExactLengthConstraint(Object other) {
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public ByteArrayExactLengthConstraint(int length) {
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(RuleContext<T, byte[]> context) {
        int len = context.getPropertyValue().length;
        return len == lengthSupplier.getAsInt();
    }
}
