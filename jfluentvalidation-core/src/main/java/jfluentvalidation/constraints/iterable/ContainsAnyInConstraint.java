package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;
import jfluentvalidation.validators.RuleContext;

import java.util.Collection;

/**
 * Checks that the subject contains at least one element that corresponds to at least one of the expected elements.
 */
public class ContainsAnyInConstraint<T, P> implements Constraint<T, Iterable<? super P>> {

    private final Iterable<? extends P> expected;

    public ContainsAnyInConstraint(Iterable<? extends P> expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        // TODO: does it make sense to move this out somewhere common?
        // TODO: collect all items that failed the constraint
        Collection<T> actual = (Collection<T>) Iterables.toCollection(context.getPropertyValue());
        for (P item : expected) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
