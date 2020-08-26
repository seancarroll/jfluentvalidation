package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class IntArrayExactLengthConstraint<T> extends AbstractConstraint<T, int[]> {

    private final IntSupplier lengthSupplier;

    public IntArrayExactLengthConstraint(Iterable<?> other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public IntArrayExactLengthConstraint(Object other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public IntArrayExactLengthConstraint(int length) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.nonnegative(length, "length");
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(ConstraintContext<T, int[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return len == lengthSupplier.getAsInt();
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, int[]> context) {
        context.getMessageContext().appendArgument("length", lengthSupplier.getAsInt());
    }
}
