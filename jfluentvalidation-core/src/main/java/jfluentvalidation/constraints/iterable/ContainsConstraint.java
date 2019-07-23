package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

/**
 * Checks that the subject contains the given element
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class ContainsConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final P element;

    // TODO: should this be a vararg / iterable?
    public ContainsConstraint(P element) {
        super(DefaultMessages.ITERABLE_CONTAINS);
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return Iterables.contains(context.getPropertyValue(), element);
    }
}
