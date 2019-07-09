package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class ObjectArrayExactLengthConstraint<T> extends AbstractConstraint<T, Object[]> {

    private final IntSupplier lengthSupplier;

    public ObjectArrayExactLengthConstraint(Iterable<?> other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public ObjectArrayExactLengthConstraint(Object other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public ObjectArrayExactLengthConstraint(int length) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(RuleContext<T, Object[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return context.getPropertyValue().length == lengthSupplier.getAsInt();
    }
}
