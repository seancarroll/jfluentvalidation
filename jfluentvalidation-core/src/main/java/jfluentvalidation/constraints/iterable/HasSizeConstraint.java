package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.function.IntSupplier;

/**
 * Check that the given {@code Iterable} being validated has the expected size.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class HasSizeConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private IntSupplier sizeSupplier;
    private int expectedSize;

    public HasSizeConstraint(int size) {
        super(DefaultMessages.HAS_SIZE);
        this.expectedSize = Ensure.nonnegative(size, "size");
    }

    public HasSizeConstraint(Iterable<?> other) {
        super(DefaultMessages.HAS_SIZE);
        Ensure.notNull(other);
        this.sizeSupplier = () -> Iterables.size(other);
    }

    public HasSizeConstraint(IntSupplier sizeSupplier) {
        super(DefaultMessages.HAS_SIZE);
        this.sizeSupplier = sizeSupplier;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        int size = Iterables.size(context.getPropertyValue());
        return size == (sizeSupplier != null ? sizeSupplier.getAsInt() : expectedSize);
    }

    @Override
    public void addParametersToContext(RuleContext<T, Iterable<? super P>> context) {
        context.getMessageContext().appendArgument("size", sizeSupplier != null ? sizeSupplier.getAsInt() : expectedSize);
    }
}
