package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

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

    private final Collection<? super P> excluded;

    public ContainsNoneConstraint(Iterable<? super P> excluded) {
        super(DefaultMessages.ITERABLE_CONTAINS_NONE);
        this.excluded = Iterables.toCollection(Ensure.notNull(excluded));
    }

    @Override
    public boolean isValid(ConstraintContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<?> actual = Iterables.toCollection(context.getPropertyValue());
        Collection<Object> present = new ArrayList<>();
        for (Object item : new LinkedHashSet<>(excluded)) {
            if (actual.contains(item)) {
                present.add(item);
            }
        }

        if (!present.isEmpty()) {
            // TODO: do I want to use this in the message?
            context.getMessageContext().appendArgument("present", present);
            return false;
        }

        return true;
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, Iterable<? super P>> context) {
        context.getMessageContext().appendArgument("values", excluded);
    }
}
