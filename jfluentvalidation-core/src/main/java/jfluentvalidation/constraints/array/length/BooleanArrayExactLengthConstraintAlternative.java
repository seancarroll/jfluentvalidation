package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.Arrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class BooleanArrayExactLengthConstraintAlternative<T> implements Constraint<T, boolean[]> {

    private final IntSupplier lengthSupplier;

    public BooleanArrayExactLengthConstraintAlternative(Iterable<?> other) {
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public BooleanArrayExactLengthConstraintAlternative(Object other) {
        Ensure.argument(Arrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public BooleanArrayExactLengthConstraintAlternative(int length) {
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(RuleContext<T, boolean[]> context) {
        int len = context.getPropertyValue().length;
        return ArrayLength.exact(len, lengthSupplier.getAsInt());
    }
}
