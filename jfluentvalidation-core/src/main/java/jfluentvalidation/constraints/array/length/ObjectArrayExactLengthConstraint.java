package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class ObjectArrayExactLengthConstraint<T, E> extends AbstractConstraint<T, E[]> {

    private final IntSupplier lengthSupplier;

    public ObjectArrayExactLengthConstraint(Iterable<E> other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public ObjectArrayExactLengthConstraint(Object other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public ObjectArrayExactLengthConstraint(E[] other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> other.length;
    }

    public ObjectArrayExactLengthConstraint(int length) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        this.lengthSupplier = () -> Ensure.nonnegative(length, "length");
    }

    @Override
    public boolean isValid(RuleContext<T, E[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length == lengthSupplier.getAsInt();
    }
}
