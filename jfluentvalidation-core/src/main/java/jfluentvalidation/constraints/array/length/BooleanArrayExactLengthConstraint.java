package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

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
        this.length = length;
    }


    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return len == (length != null ? length : lengthSupplier.getAsInt());
    }
}
