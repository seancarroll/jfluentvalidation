package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.Constraint;

import java.util.Collection;

/**
 * Checks that the subject contains at least one element that corresponds to at least one of the expected elements.
 */
public class ContainsAnyInConstraint<T> implements Constraint<Iterable<? super T>> {

    private final Iterable<? super T> expected;

    public ContainsAnyInConstraint(Iterable<? super T> expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(Iterable<? super T> value) {
        // TODO: does it make sense to move this out somewhere common?
        Collection<T> actual = (Collection<T>) Iterables.toCollection(value);
        for (Object item : expected) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
