package jfluentvalidation.constraints.iterable;

import jfluentvalidation.common.Iterables;
import jfluentvalidation.constraints.AbstractConstraint;
import jfluentvalidation.constraints.DefaultMessages;
import jfluentvalidation.internal.Ensure;
import jfluentvalidation.validators.ConstraintContext;

import java.util.Collection;
import java.util.List;

import static jfluentvalidation.common.Lists.newArrayList;

public class ContainsExactlyInAnyOrderConstraint <T, P> extends AbstractConstraint<T, Iterable<? super P>> {

    private final Iterable<P> expected;

    public ContainsExactlyInAnyOrderConstraint(Iterable<P> expected) {
        super(DefaultMessages.ITERABLE_CONTAINS_EXACTLY);
        this.expected = Ensure.notNull(expected);
    }

    @Override
    public boolean isValid(ConstraintContext<T, Iterable<? super P>> context) {
        if (context.getPropertyValue() == null) {
            return true;
        }

        Collection<? super P> actual = Iterables.toCollection(context.getPropertyValue());

        List<Object> notExpected = newArrayList(actual);
        List<Object> notFound = newArrayList(expected);

        for (Object value : expected) {
            if (Iterables.contains(notExpected, value)) {
                Iterables.removeFirst(notExpected, value);
                Iterables.removeFirst(notFound, value);
            }
        }

        if (notExpected.isEmpty() && notFound.isEmpty()) {
            return true;
        }

        if (!notFound.isEmpty()) {
            context.getMessageContext().appendArgument("missingValues", notFound);
        }

        if (!notExpected.isEmpty()) {
            context.getMessageContext().appendArgument("unexpectedValues", notExpected);
        }

        return false;
    }

    @Override
    public void addParametersToContext(ConstraintContext<T, Iterable<? super P>> context) {
        context.getMessageContext().appendArgument("expected", expected);
    }
}
