package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class CharArrayExactLengthConstraint<T> extends AbstractConstraint<T, char[]> {

    private final IntSupplier lengthSupplier;

    public CharArrayExactLengthConstraint(Iterable<?> other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public CharArrayExactLengthConstraint(Object other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public CharArrayExactLengthConstraint(int length) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        int len = context.getPropertyValue().length;
        return len == lengthSupplier.getAsInt();
    }
}
