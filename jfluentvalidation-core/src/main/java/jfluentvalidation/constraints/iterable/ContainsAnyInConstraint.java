package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

import java.util.Collection;

/**
 * Checks that the subject contains at least one element that corresponds to at least one of the expected elements.
 */
public class ContainsAnyInConstraint<T> implements Constraint<Iterable<? super T>> {

    private final Iterable<? extends T> expected;

    public ContainsAnyInConstraint(Iterable<? extends T> expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(Iterable<? super T> value) {
        // TODO: does it make sense to move this out somewhere common?
        // TODO: collect all items that failed the constraint
        Collection<T> actual = (Collection<T>) Iterables.toCollection(value);
        for (T item : expected) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
