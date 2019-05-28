package jfluentvalidation.constraints.array.length;

import jfluentvalidation.common.MoreArrays;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.lang.reflect.Array;
import java.util.function.IntSupplier;

public class CharArrayExactLengthConstraint<T> implements Constraint<T, char[]> {

    private final IntSupplier lengthSupplier;

    public CharArrayExactLengthConstraint(Iterable<?> other) {
        Ensure.notNull(other);
        this.lengthSupplier = () -> Iterables.size(other);
    }

    public CharArrayExactLengthConstraint(Object other) {
        Ensure.argument(MoreArrays.isArray(other));
        this.lengthSupplier = () -> Array.getLength(other);
    }

    public CharArrayExactLengthConstraint(int length) {
        this.lengthSupplier = () -> length;
    }

    @Override
    public boolean isValid(RuleContext<T, char[]> context) {
        int len = context.getPropertyValue().length;
        return len == lengthSupplier.getAsInt();
    }
}
