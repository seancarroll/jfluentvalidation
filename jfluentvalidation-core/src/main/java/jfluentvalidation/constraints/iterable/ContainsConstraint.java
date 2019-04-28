package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

/**
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class ContainsConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    private final P element;

    public ContainsConstraint(P element) {
        this.element = element;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        return Iterables.contains(context.getPropertyValue(), element);
    }
}
