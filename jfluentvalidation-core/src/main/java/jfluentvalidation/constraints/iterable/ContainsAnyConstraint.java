package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.RuleContext;

import java.util.Collection;


/**
 * Checks that the subject contains at least one element that corresponds to at least one of the expected {@code values}.
 *
 * @param <T>  type of instance to validate.
 * @param <P>  the type of the actual object being tested by this {@code Constraint}.
 */
public class ContainsAnyConstraint<T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<P> values;

    public ContainsAnyConstraint(Iterable<P> values) {
        super(DefaultMessages.ITERABLE_CONTAINS_ANY);
        this.values = Ensure.notNull(values);
    }

    @Override
    public boolean isValid(RuleContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        // if both actual and values are empty constraint should be valid
        if (!context.getPropertyValue().iterator().hasNext() && !values.iterator().hasNext()) {
            return true;
        }

        Collection<? super P> actual = Iterables.toCollection(context.getPropertyValue());
        for (P item : values) {
            if (actual.contains(item)) {
                return true;
            }
        }

        return false;
    }
}
