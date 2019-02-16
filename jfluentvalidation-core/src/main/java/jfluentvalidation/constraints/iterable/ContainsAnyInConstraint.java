package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

import java.util.Collection;

/**
 * Checks that the subject contains at least one element that corresponds to at least one of the expected elements.
 */
public class ContainsAnyInConstraint implements Constraint<Iterable<?>> {

    private final Iterable<?> expected;

    public ContainsAnyInConstraint(Iterable<?> expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        // TODO: does it make sense to move this out somewhere common?
        Collection<?> actual = Iterables.toCollection(instance);
        for (Object item : expected) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
