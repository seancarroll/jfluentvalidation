package jfluentvalidation.constraints.iterable;

import com.google.common.collect.Sets;
import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.ArrayList;
import java.util.Collection;

/**
 * TODO: From Google Truth...we want similar behavior
 * Checks that the subject contains no elements that correspond to any of the given elements.
 * (Duplicates are irrelevant to this test, which fails if any of the subject elements
 * correspond to any of the given elements.)
 */

/**
 * Verifies that the given <code>{@link Iterable}</code> does not contain the given elements.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class ContainsNoneConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<? super P> excluded;

    public ContainsNoneConstraint(Iterable<? super P> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE_IN);
        this.excluded = Ensure.notNull(excluded);
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<?> actual = Iterables.toCollection(context.getPropertyValue());
        Collection<Object> present = new ArrayList<>();

        // TODO: remove guava
        for (Object item : Sets.newLinkedHashSet(excluded)) {
            if (actual.contains(item)) {
                present.add(item);
            }
        }

        if (!present.isEmpty()) {
            // TODO: add to arguments
            return false;
        }

        return true;
    }

//    checkIsNotNullAndNotEmpty(values);
//    assertNotNull(info, actual);
//    Set<Object> found = new LinkedHashSet<>();
//    for (Object o : values) {
//        if (iterableContains(actual, o)) found.add(o);
//    }
//    if (!found.isEmpty()) throw failures.failure(info, shouldNotContain(actual, values, found, comparisonStrategy));
}
