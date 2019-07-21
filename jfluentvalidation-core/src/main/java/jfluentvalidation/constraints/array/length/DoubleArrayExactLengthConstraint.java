package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class DoubleArrayExactLengthConstraint<T> extends AbstractConstraint<T, double[]> {

    private final IntSupplier lengthSupplier;

    public DoubleArrayExactLengthConstraint(Iterable<?> other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public DoubleArrayExactLengthConstraint(Object other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public DoubleArrayExactLengthConstraint(int length) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.nonnegative(length, "length");
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(RuleContext<T, double[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return len == lengthSupplier.getAsInt();
    }
}
