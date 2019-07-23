package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.IterableDifference;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static com.google.common.collect.Lists.newArrayList;

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
public class ContainsExactlyConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<P> expected;

    public ContainsExactlyConstraint(Iterable<P> expected) {
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY_ELEMENTS_IN);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<P> actual = (Collection<P>) Iterables.toCollection(context.getPropertyValue());
        // TODO: dont use guava
        List<P> expectedAsList = newArrayList(expected);


        // TODO: diff
        IterableDifference<P> diff = IterableDifference.diff(actual, expectedAsList);
        if (!diff.differencesFound()) {
            int i = 0;
            for (Object elementFromActual : actual) {
                if (!Objects.equals(elementFromActual, expectedAsList.get(i))) {
                    // context.appendArgument("missingValues", notFound);
                    return false;
                }
                i++;
            }
            return true;
        }

        if (diff.hasMissing()) {
            // context.appendArgument("missingValues", notFound);
        }

        if (diff.hasUnexpected()) {
            // context.appendArgument("unexpectedValues", notFound);
        }
        return false;
    }
}