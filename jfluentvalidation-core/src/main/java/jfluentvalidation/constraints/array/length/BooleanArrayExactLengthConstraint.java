package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class BooleanArrayExactLengthConstraint<T> extends AbstractConstraint<T, boolean[]> {

    private IntSupplier lengthSupplier;
    private Integer length;

    public BooleanArrayExactLengthConstraint(Iterable<?> other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public BooleanArrayExactLengthConstraint(Object other) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public BooleanArrayExactLengthConstraint(int length) {
        super(DefaultMessages.ARRAY_EXACT_LENGTH);
        Ensure.nonnegative(length, "length");
        this.length = length;
    }


    @Override
    public boolean isValid(ConstraintContext<T, boolean[]> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        int len = context.getPropertyValue().length;
        return len == (length != null ? length : lengthSupplier.getAsInt());
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, boolean[]> context) {
        context.getMessageContext().appendArgument("length", (length != null ? length : lengthSupplier.getAsInt()));
    }
}
