package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toCollection;

// AKA containsAllOf
/**
 * TODO: from Google Truth...we want something similar ot this behavior
 * Checks that the actual iterable contains at least all of the expected elements or fails. If an
 * element appears more than once in the expected elements to this call then it must appear at
 * least that number of times in the actual elements.
 *
 * <p>To also test that the contents appear in the given order, make a call to {@code inOrder()}
 * on the object returned by this method. The expected elements must appear in the given order
 * within the actual elements, but they are not required to be consecutive.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class ContainsAllOfConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<P> expectedIterable;

    public ContainsAllOfConstraint(Iterable<P> expectedIterable) {
        super(DefaultMessages.ITERABLE_CONTAINS_ALL_IN);
        this.expectedIterable = Ensure.notNull(expectedIterable);
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }
        Object[] values = Iterables.toArray(expectedIterable);
        Set<Object> notFound = stream(values)
            .filter(value -> !Iterables.contains(context.getPropertyValue(), value))
            .collect(toCollection(LinkedHashSet::new));

        if (!notFound.isEmpty()) {
            context.appendArgument("missingValues", notFound);
        }

        return notFound.isEmpty();
    }

}
