package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 * Check that the given {@code Iterable} being validated has the expected size.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class HasSizeConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final int expectedSize;

    public HasSizeConstraint(int expectedSize) {
        super(DefaultMessages.HAS_SIZE);
        this.expectedSize = Ensure.nonnegative(expectedSize, "expectedSize");
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return Iterables.size(context.getPropertyValue()) == expectedSize;
    }

}
