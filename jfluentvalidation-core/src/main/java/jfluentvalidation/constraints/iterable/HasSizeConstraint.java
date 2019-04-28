package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class HasSizeConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    private final int expectedSize;

    public HasSizeConstraint(int expectedSize) {
        this.expectedSize = Ensure.nonnegative(expectedSize, "expectedSize");
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return Iterables.size(context.getPropertyValue()) == expectedSize;
    }

}
