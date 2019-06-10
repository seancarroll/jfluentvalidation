package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.validators.RuleContext;

import java.util.Collection;

/**
 * Checks that the subject contains at least one element that corresponds to at least one of the expected elements.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class ContainsAnyInConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<P> expected;

    public ContainsAnyInConstraint(Iterable<P> expected) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY_IN);
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        Collection<? super P> actual = Iterables.toCollection(context.getPropertyValue());
        for (P item : expected) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
