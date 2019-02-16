package jfluentvalidation.constraints.iterable;

import jfluentvalidation.constraints.Constraint;

/**
 * TODO: From Google Truth...we want similar behavior
 * Checks that a subject contains exactly the provided objects or fails.
 *
 * <p>Multiplicity is respected. For example, an object duplicated exactly 3 times in the
 * parameters asserts that the object must likewise be duplicated exactly 3 times in the subject.
 *
 * <p>To also test that the contents appear in the given order, make a call to {@code inOrder()}
 * on the object returned by this method.
 *
 * <p>To test that the iterable contains the same elements as an array, prefer {@link
 * #containsExactlyElementsIn(Object[])}. It makes clear that the given array is a list of
 * elements, not an element itself. This helps human readers and avoids a compiler warning.
 */
public class ContainsExactlyElementsInConstraint implements Constraint<Iterable<?>> {

    private final Iterable<?> expected;

    public ContainsExactlyElementsInConstraint(Iterable<?> expected) {
        this.expected = expected;
    }

    @Override
    public boolean isValid(Iterable<?> instance) {
        return false;
    }
}
