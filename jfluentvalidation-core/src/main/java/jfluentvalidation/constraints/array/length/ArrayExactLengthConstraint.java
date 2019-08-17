package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class ArrayExactLengthConstraint<T, A> extends AbstractConstraint<T, A> {

    private IntSupplier lengthSupplier;
    private int length;

    public ArrayExactLengthConstraint(Iterable<?> other) {
        this(() -> Iterables.size(other));
    }

    public ArrayExactLengthConstraint(Object other) {
        this(() -> Array.getLength(other));
    }

    public ArrayExactLengthConstraint(int length) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        this.length = Ensure.nonnegative(length, "length");
    }

    public ArrayExactLengthConstraint(IntSupplier lengthSupplier) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        this.lengthSupplier = lengthSupplier;
    }

    @Override
    public boolean isValid(RuleContext<T, A> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = Array.getLength(context.getPropertyValue());
        return len == (lengthSupplier != null ? lengthSupplier.getAsInt() : length);
    }
}
