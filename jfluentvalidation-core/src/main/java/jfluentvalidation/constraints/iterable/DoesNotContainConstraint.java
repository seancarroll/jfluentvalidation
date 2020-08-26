package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.ConstraintContext;

/**
 * Verifies that the given <code>{@link Iterable}</code> does not contain the given element.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class DoesNotContainConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final P element;

    // TODO: should this be vararg?
    public DoesNotContainConstraint(P element) {
        super(DefaultMessages.ITERABLE_DOES_NOT_CONTAIN);
        this.element = element;
    }

    @Override
    public boolean isValid(ConstraintContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        return !Iterables.contains(context.getPropertyValue(), element);
    }
}
